$('#save-weapon-btn').on('click', function(event) {
	postSimpleForm('#save-weapon-form');
});

var fillOwnerData = function(owner) {
	$("#weaponOwnerId").val(owner.id);
	$("#weaponOwnerFirstName").val(owner.firstName);
	$("#weaponOwnerLastName").val(owner.lastName);
	$("#weaponOwnerBirthDate").val(dateString(owner.birthDate));
	$("#weaponOwnerMbg").val(owner.idNumber);
}

$(document.body).on('click', '.btn-choose-person', function(event) {
	let owner = $(event.target).data("owner");
	fillOwnerData(owner);
	
});