package com.bytepoet.undp.platformdemo.services;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.apache.poi.xwpf.usermodel.XWPFTable;
import org.apache.poi.xwpf.usermodel.XWPFTableCell;
import org.apache.poi.xwpf.usermodel.XWPFTableRow;

public class TextReplacer {
	private String searchValue;
	private String replacement;

	public TextReplacer(String searchValue, String replacement) {
		this.searchValue = searchValue;
		if(StringUtils.isBlank(replacement)) {
			replacement = "/";
		}
		this.replacement = replacement;
	}
	
	public TextReplacer(String searchValue, long replacement) {
		this.searchValue = searchValue;
		this.replacement = String.valueOf(replacement);
	}

	public TextReplacer(String searchValue, Date replacement) {
		this.searchValue = searchValue;
		DateFormat df = new SimpleDateFormat("dd.MM.yyyy.");
		if(replacement == null) {
			this.replacement = "/";
		} else {
			this.replacement = df.format(replacement);
		}
		
	}

	public void replace(XWPFDocument document) {
		List<XWPFParagraph> paragraphs = document.getParagraphs();
		
		for (XWPFParagraph xwpfParagraph : paragraphs) {
			replace(xwpfParagraph);
		}
		List<XWPFTable> tables = document.getTables();
		for (XWPFTable xwpfTable : tables) {
			replace(xwpfTable);
		}
	}

	private void replace(XWPFParagraph paragraph) {
		if (hasReplaceableItem(paragraph.getText())) {
			String replacedText = StringUtils.replace(paragraph.getText(), searchValue, replacement);

			removeAllRuns(paragraph);

			insertReplacementRuns(paragraph, replacedText);
		}
	}
	
	private void replace(XWPFTable table) {
		for (XWPFTableRow row : table.getRows()) {
			for (XWPFTableCell cell : row.getTableCells()) {
				List<XWPFParagraph> paragraphs = cell.getParagraphs();
				for (XWPFParagraph xwpfParagraph : paragraphs) {
					replace(xwpfParagraph);
				}
			}
		}
	}

	private void insertReplacementRuns(XWPFParagraph paragraph, String replacedText) {
		String[] replacementTextSplitOnCarriageReturn = StringUtils.split(replacedText, "\n");

		for (int j = 0; j < replacementTextSplitOnCarriageReturn.length; j++) {
			String part = replacementTextSplitOnCarriageReturn[j];

			XWPFRun newRun = paragraph.insertNewRun(j);
			newRun.setText(part);

			if (j+1 < replacementTextSplitOnCarriageReturn.length) {
				newRun.addCarriageReturn();
			}
		}       
	}

	private void removeAllRuns(XWPFParagraph paragraph) {
		int size = paragraph.getRuns().size();
		for (int i = 0; i < size; i++) {
			paragraph.removeRun(0);
		}
	}

	private boolean hasReplaceableItem(String runText) {
		return StringUtils.contains(runText, searchValue);
	}
}
