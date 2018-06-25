$('#save-vehicle-btn').on('click', function(event) {
	postSimpleForm('#save-vehicle-form');
});

var fillOwnerData = function(owner) {
	$("#vehicleOwnerId").val(owner.id);
	$("#vehicleOwnerfirstName").val(owner.firstName);
	$("#vehicleOwnerLastName").val(owner.lastName);
	$("#vehicleOwnerBirthDate").val(dateString(owner.birthDate));
	$("#vehicleOwnerMbg").val(owner.idNumber);
}

$(document.body).on('click', '.btn-choose-person', function(event) {
	let owner = $(event.target).data("owner");
	fillOwnerData(owner);
	
});