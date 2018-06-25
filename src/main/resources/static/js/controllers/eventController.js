$('#save-event').on('click', function(event) {
	let formData = $('#save-event-form').serializeArray();
	let jsonObject = {};
	for (let i = 0; i < formData.length; i++) {
		jsonObject[formData[i].name] = formData[i].value;
	}
	
	$('.event-person-row').each(function(index, element) {
		let id = $(element).data("id")
		if (!jsonObject["persons"]) {
			jsonObject["persons"] = [];
		}
		jsonObject["persons"].push({id: id})
	});
	$('.event-vehicle-row').each(function(index, element) {
		let id = $(element).data("id")
		if (!jsonObject["vehicles"]) {
			jsonObject["vehicles"] = [];
		}
		jsonObject["vehicles"].push({id: id})
	});
	$('.event-weapon-row').each(function(index, element) {
		let id = $(element).data("id")
		if (!jsonObject["weapons"]) {
			jsonObject["weapons"] = [];
		}
		jsonObject["weapons"].push({id: id})
	});
	let json = JSON.stringify(jsonObject);
	let serverUrl = $('#save-event-form').attr('action');
	$.ajax({
		type : "POST",
		url : serverUrl,
		data : json,
		success : function() {
			window.open("/","_self")
		},
		error : function(arg1, arg2, arg3) {
			console.log(arg1);
			console.log(arg2);
			console.log(arg3);
			$("#result-id").text("Spremanje nije uspjelo")
		},
		dataType : "json",
		contentType : "application/json"
	});
});

$(document.body).on('click', '.btn-choose-person', function(event) {
	var onChoose = $("#find-person-modal").data("onChoose");
	onChoose(event);
});

$(document.body).on('click', '.btn-choose-vehicle', function(event) {
	let vehicle = $(event.target).data("vehicle");
	addVehicle(vehicle);
});

$(document.body).on('click', '.btn-choose-weapon', function(event) {
	let weapon = $(event.target).data("weapon");
	addWeapon(weapon);
	
});

var addPerson = function(person) {
	let row = '<tr class="event-person-row"><td class="firstname"/><td class="lastname"/><td class="birthdate"/><td class="mbg"/><td><button class="btn btn-default btn-delete">Obriši</button></td>';
	
	let rowObject = $(row);
	rowObject.find(".firstname").text(person.firstName);
	rowObject.find(".lastname").text(person.lastName);
	rowObject.find(".birthdate").text(dateString(person.birthDate));
	rowObject.find(".mbg").text(person.idNumber);
	rowObject.data("id", person.id);
	
	$("#event-persons-body").append(rowObject);
}

$('#save-person-btn').on('click', function(event) {
	postSimpleForm('#save-person-form', function(result) {
		addPerson(result);
		$('#insert-person-modal').modal('hide');
	});
	
});



var addVehicle = function(vehicle) {
	let row = '<tr class="event-vehicle-row"><td class="regnumber"/><td class="manufacturer"/><td class="model"/><td><button class="btn btn-default btn-delete">Obriši</button></td>';
	
	let rowObject = $(row);
	rowObject.find(".regnumber").text(vehicle.registrationNumber);
	rowObject.find(".manufacturer").text(vehicle.manufacturer);
	rowObject.find(".model").text(vehicle.model);
	rowObject.data("id", vehicle.id);
	
	$("#event-vehicles-body").append(rowObject);
}

$('#save-vehicle-btn').on('click', function(event) {
	postSimpleForm('#save-vehicle-form', function(result) {
		addVehicle(result);
		$('#insert-vehicle-modal').modal('hide');
	});
});

var addWeapon = function(weapon) {
	let row = '<tr class="event-weapon-row"><td class="idnumber"/><td class="manufacturer"/><td class="model"/><td><button class="btn btn-default btn-delete">Obriši</button></td>';
	
	let rowObject = $(row);
	rowObject.find(".idnumber").text(weapon.idNumber);
	rowObject.find(".manufacturer").text(weapon.manufacturer);
	rowObject.find(".model").text(weapon.model);
	rowObject.data("id", weapon.id);
	
	$("#event-weapons-body").append(rowObject);
}

$('#save-weapon-btn').on('click', function(event) {
	postSimpleForm('#save-weapon-form', function(result) {
		addWeapon(result);
		$('#insert-weapon-modal').modal('hide');
	});
});

$('#find-event-person-btn').on('click', function() {
	$("#table-persons-body-id").empty();
	$("#find-person-modal").data("onChoose", function(event) {
		let person = $(event.target).data("owner");
		addPerson(person);
	});
});

$('#find-vehicle-owner-btn').on('click', function() {
	$("#table-persons-body-id").empty();
	$("#find-person-modal").data("onChoose", function(event) {
		let owner = $(event.target).data("owner");
		$("#vehicleOwnerId").val(owner.id);
		$("#vehicleOwnerfirstName").val(owner.firstName);
		$("#vehicleOwnerLastName").val(owner.lastName);
		$("#vehicleOwnerBirthDate").val(owner.birthDate);
		$("#vehicleOwnerMbg").val(owner.mbg);
	});
});

$('#find-weapon-owner-btn').on('click', function() {
	$("#table-persons-body-id").empty();
	$("#find-person-modal").data("onChoose", function(event) {
		let owner = $(event.target).data("owner");
		$("#weaponOwnerId").val(owner.id);
		$("#weaponOwnerFirstName").val(owner.firstName);
		$("#weaponOwnerLastName").val(owner.lastName);
		$("#weaponOwnerBirthDate").val(owner.birthDate);
		$("#weaponOwnerMbg").val(owner.mbg);
	});
});

$('#find-vehicle-btn').on('click', function() {
	$("#table-vehicles-body-id").empty();
});

$('#find-weapon-btn').on('click', function() {
	$("#table-weapons-body-id").empty();
});


$(document.body).on("click", ".btn-delete", function(event) {
	let row = $(event.currentTarget).closest('tr');
	row.remove();
})

var loadEvent = function() {
	loadSimpleForm('#save-event-form', function(data) {
		if(data.persons !== undefined) {
			for (let i = 0; i < data.persons.length; i++) {
				addPerson(data.persons[i]);
			}
		}
		if(data.vehicles !== undefined) {
			for (let i = 0; i < data.vehicles.length; i++) {
				addVehicle(data.vehicles[i]);
			}
		}
		if(data.weapons !== undefined) {
			for (let i = 0; i < data.weapons.length; i++) {
				addWeapon(data.weapons[i]);
			}
		}
		
		if (data.id) {
			$(".btn-print-group").show();
			$(".event-print-form").attr("href", window.location.origin + "/events/" + data.id + "/print/FORM");
			$(".event-print-zkp").attr("href", window.location.origin + "/events/" + data.id + "/print/ZKP");
			$(".event-print-zop").attr("href", window.location.origin + "/events/" + data.id + "/print/ZOP");
			$(".event-print-crim").attr("href", window.location.origin + "/events/" + data.id + "/print/CRIM");
		}
		
		
		
	});
}