//closing current window
$('#close').on("click", function() {
    window.close();
})

function returnObject(alies, optional) {
    switch (alies) {
        case "department":
            return "assignments_department";
            break;
        case "designation":
            return "assignments_designation";
            break;
        case "position":
            return "assignments_position";
            break;
        case "mainDepartments":
            return "assignments_department";
            break;
        case "fund":
            return "assignments_fund";
            break;
        case "function":
            return "assignments_function";
            break;
        case "functionary":
            return "assignments_functionary";
            break;
        case "grade":
            return "assignments_grade";
            break;
        case "jurisdictionsType":
            return "jurisdictions_jurisdictionsType";
            break;
        case "boundary":
            if (optional == "CITY") {
                return "juridictionTypeForCity";
            } else if (optional == "WARD") {
                return "juridictionTypeForWard";
            } else {
                return "juridictionTypeForZone";
            }
            break;
        default:

    }

}


// localStorage.setItem("employeeType",JSON.stringify(getCommonMaster("hr-masters", "employeetypes", "EmployeeType").responseJSON["EmployeeType"]));
// var employeeType=JSON.parse(localStorage.getItem("employeeType"))==""?(localStorage.setItem("employeeType",JSON.stringify(getCommonMaster("hr-masters", "employeetypes", "EmployeeType").responseJSON["EmployeeType"]))|| []) :JSON.parse(localStorage.getItem("employeeType"));
// console.log(employeeType);
//common object
var yearOfPassing = [];

for (var i = 2000; i <= new Date().getFullYear(); i++) {
    yearOfPassing.push(i);
}

var hrConfigurations = commonApiPost("hr-masters", "hrconfigurations", "_search", {
    tenantId
}).responseJSON || [];
if (hrConfigurations["HRConfiguration"]["Autogenerate_employeecode"] == "N") {
    $("#code").prop("disabled", false);
} else {
    $("#code").prop("disabled", true);
}

// console.log(yearOfPassing);
// var commonObject = {
//     employeeType: getCommonMaster("hr-masters", "employeetypes", "EmployeeType").responseJSON["EmployeeType"] || [],
//     employeeStatus: getCommonMaster("hr-masters", "hrstatuses", "HRStatus").responseJSON["HRStatus"] || [],
//     group: getCommonMaster("hr-masters", "groups", "Group").responseJSON["Group"] || [],
//     maritalStatus: ["MARRIED", "UNMARRIED", "DIVORCED", "WIDOWER", "WIDOW"],
//     user_bloodGroup: ["O-", "O+", "A-", "A+", "B-", "B+", "AB-", "AB+"],
//     motherTounge: getCommonMaster("egov-common-masters", "languages", "Language").responseJSON["Language"] || [],
//     religion: getCommonMaster("egov-common-masters", "religions", "Religion").responseJSON["Religion"] || [],
//     community: getCommonMaster("egov-common-masters", "communities", "Community").responseJSON["Community"] || [],
//     category: getCommonMaster("egov-common-masters", "categories", "Category").responseJSON["Category"] || [],
//     bank: getCommonMaster("egf-masters", "banks", "banks").responseJSON["banks"] || [],
//     bankBranch: [],
//     recruitmentMode: getCommonMaster("hr-masters", "recruitmentmodes", "RecruitmentMode").responseJSON["RecruitmentMode"] || [],
//     recruitmentType: getCommonMaster("hr-masters", "recruitmenttypes", "RecruitmentType").responseJSON["RecruitmentType"] || [],
//     recruitmentQuota: getCommonMaster("hr-masters", "recruitmentquotas", "RecruitmentQuota").responseJSON["RecruitmentQuota"] || [],
//     assignments_fund: [{
//             id: 1,
//             name: "Own",
//             description: ""
//         },
//         {
//             id: 2,
//             name: "Company",
//             description: ""
//         }
//     ],
//     assignments_function: [{
//             id: 1,
//             name: "IR",
//             description: ""
//         },
//         {
//             id: 2,
//             name: "No-IT",
//             description: ""
//         }
//     ],
//     assignments_functionary: [{
//             id: 1,
//             name: "developrment",
//             description: ""
//         },
//         {
//             id: 2,
//             name: "no developrment",
//             description: ""
//         }
//     ],
//     assignments_grade: getCommonMaster("hr-masters", "grades", "Grade").responseJSON["Grade"] || [],
//     assignments_designation: getCommonMaster("hr-masters", "designations", "Designation").responseJSON["Designation"] || [],
//     assignments_position: [],
//     assignments_department: getCommonMaster("egov-common-masters", "departments", "Department").responseJSON["Department"] || [],
//     jurisdictions_jurisdictionsType: [{
//             id: "CITY",
//             name: "City",
//             description: "",
//             active: true
//         },
//         {
//             id: "WARD",
//             name: "Ward",
//             description: "",
//             active: true
//         },
//         {
//             id: "ZONE",
//             name: "Zone",
//             description: "",
//             active: true
//         }
//     ],
//     jurisdictions_boundary: [],
//     yearOfPassing
// }
// var juridictionTypeForCity=[];
// var juridictionTypeForWard=[];
// var juridictionTypeForZone=[];


var commonObject = {
    employeeType,
    employeeStatus,
    group,
    maritalStatus,
    user_bloodGroup,
    motherTounge,
    religion,
    community,
    category,
    bank,
    bankBranch: [],
    recruitmentMode,
    recruitmentType,
    recruitmentQuota,
    assignments_fund,
    assignments_function,
    assignments_functionary,
    assignments_grade,
    assignments_designation,
    assignments_position: [],
    assignments_department,
    jurisdictions_jurisdictionsType: [{
            id: "CITY",
            name: "City",
            description: "",
            active: true
        },
        {
            id: "WARD",
            name: "Ward",
            description: "",
            active: true
        },
        {
            id: "ZONE",
            name: "Zone",
            description: "",
            active: true
        }
    ],
    jurisdictions_boundary: [],
    yearOfPassing,
    juridictionTypeForCity: [],
    juridictionTypeForWard: [],
    juridictionTypeForZone: []
}

// getCommonMaster("hr-masters", "positions", "Position").responseJSON["Position"] || [];

//common shared object
commonObject["assignments_mainDepartments"] = commonObject["assignments_department"];
commonObject["languagesKnown"] = commonObject["motherTounge"];
commonObject["user_locale"] = commonObject["motherTounge"];
commonObject["probation_designation"] = commonObject["assignments_designation"];
commonObject["regularisation_designation"] = commonObject["assignments_designation"];
commonObject["education_yearOfPassing"] = commonObject["yearOfPassing"];
commonObject["technical_yearOfPassing"] = commonObject["yearOfPassing"];
commonObject["test_yearOfPassing"] = commonObject["yearOfPassing"];

for (var key in commonObject) {
    var splitObject = key.split("_");
    if (splitObject.length < 2) {
        for (var i = 0; i < commonObject[key].length; i++) {
            if (typeof(commonObject[key][i]) === "object")
                $(`#${key}`).append(`<option value='${commonObject[key][i]['id']}'>${typeof(commonObject[key][i]['name'])=="undefined"?commonObject[key][i]['code']:commonObject[key][i]['name']}</option>`)
            else
                $(`#${key}`).append(`<option value='${commonObject[key][i]}'>${commonObject[key][i]}</option>`)
        }
    } else {
        for (var i = 0; i < commonObject[key].length; i++) {
            if (typeof(commonObject[key][i]) === "object")
                $(`#${splitObject[0]}\\.${splitObject[1]}`).append(`<option value='${commonObject[key][i]['id']}'>${commonObject[key][i]['name']}</option>`)
            else
                $(`#${splitObject[0]}\\.${splitObject[1]}`).append(`<option value='${commonObject[key][i]}'>${commonObject[key][i]}</option>`)
        }
    }
}


function getNameById(object, id, optional) {
    // console.log(commonObject[object].length);
    // return commonObject[object].length
    object = returnObject(object, optional);
    for (var i = 0; i < commonObject[object].length; i++) {
        if (commonObject[object][i].id == id) {
            return commonObject[object][i].name;
        }
    }
    return "";
}

var tempListBox = [];
//final post object
var employee = {
    code: "",
    dateOfAppointment: "",
    dateOfJoining: "",
    dateOfRetirement: "",
    employeeStatus: "",
    recruitmentMode: "",
    recruitmentType: "",
    recruitmentQuota: "",
    retirementAge: "",
    dateOfResignation: "",
    dateOfTermination: "",
    employeeType: "",
    assignments: [],
    jurisdictions: [],
    motherTounge: "",
    religion: "",
    community: "",
    category: "",
    physicallyDisabled: false,
    medicalReportProduced: true,
    languagesKnown: [],
    maritalStatus: "",
    passportNo: null,
    gpfNo: null,
    bank: "",
    bankBranch: "",
    bankAccount: "",
    group: "",
    placeOfBirth: "",
    documents: null,
    serviceHistory: [],
    probation: [],
    regularisation: [],
    technical: [],
    education: [],
    test: [],
    user: {
        userName: "",
        name: "",
        gender: "",
        mobileNumber: "",
        emailId: "",
        altContactNumber: "",
        pan: "",
        aadhaarNumber: "",
        permanentAddress: "",
        permanentCity: "",
        permanentPincode: "",
        correspondenceCity: "",
        correspondencePincode: "",
        correspondenceAddress: "",
        active: true,
        dob: "",
        locale: "",
        signature: "",
        fatherOrHusbandName: "",
        bloodGroup: "",
        identificationMark: "",
        photo: "",
        type: "EMPLOYEE",
        password: "12345678",
        tenantId
    },
    tenantId
}

//temprory object for holding modal value
var employeeSubObject = {
    assignments: {
        fromDate: "",
        toDate: "",
        department: "",
        designation: "",
        position: "",
        isPrimary: false,
        fund: "",
        function: "",
        functionary: "",
        grade: "",
        hod: false,
        // mainDepartments: "",
        govtOrderNumber: "",
        documents:null
    },
    jurisdictions: {
        jurisdictionsType: "",
        boundary: ""
    },
    serviceHistory: {
        id: 1,
        serviceInfo: "",
        serviceFrom: "",
        remarks: "",
        orderNo: "",
        documents: null
    },
    probation: {
        designation: "",
        declaredOn: "",
        orderNo: "",
        orderDate: "",
        remarks: "",
        documents: null
    },
    regularisation: {
        designation: "",
        declaredOn: "",
        orderNo: "",
        orderDate: "",
        remarks: "",
        documents: null
    },
    education: {
        qualification: "",
        majorSubject: "",
        yearOfPassing: "",
        university: "",
        documents: null
    },
    technical: {
        skill: "",
        grade: "",
        yearOfPassing: "",
        remarks: "",
        documents: null
    },
    test: {
        test: "",
        yearOfPassing: "",
        remarks: "",
        documents: null
    }
}

//unicersal marker for putting edit index
var editIndex = -1;




//form validation
var validation_rules = {};
var final_validatin_rules = {};
var commom_fields_rules = {
    code: {
        required: true
    },
    dateOfAppointment: {
        required: true
    },
    dateOfJoining: {
        required: false
    },
    dateOfRetirement: {
        required: false
    },
    employeeStatus: {
        required: true
    },
    recruitmentMode: {
        required: false
    },
    recruitmentType: {
        required: false
    },
    recruitmentQuota: {
        required: false
    },
    retirementAge: {
        required: false
    },
    dateOfResignation: {
        required: false
    },
    dateOfTermination: {
        required: false
    },
    employeeType: {
        required: true
    },
    motherTounge: {
        required: false
    },
    religion: {
        required: false
    },
    community: {
        required: false
    },
    category: {
        required: false
    },
    physicallyDisabled: {
        required: false
    },
    medicalReportProduced: {
        required: false
    },
    languagesKnown: {
        required: false
    },
    maritalStatus: {
        required: true
    },
    passportNo: {
        required: false
    },
    gpfNo: {
        required: false
    },
    bank: {
        required: false
    },
    bankBranch: {
        required: false
    },
    bankAccount: {
        required: false
    },
    group: {
        required: false
    },
    placeOfBirth: {
        required: false
    },
    documents: {
        required: false
    },
    "user.userName": {
        required: true
    },
    "user.name": {
        required: true
    },
    "user.gender": {
        required: true
    },
    "user.mobileNumber": {
        required: true
        // ,
        // phone:true
    },
    "user.emailId": {
        required: false
    },
    "user.altContactNumber": {
        required: false
        // ,
        // phone:true
    },
    "user.pan": {
        required: false
        // ,
        // panNo:true
    },
    "user.aadhaarNumber": {
        required: false
        // ,
        // aadhar:true
    },
    "user.permanentAddress": {
        required: true
    },
    "user.permanentCity": {
        required: true
    },
    "user.permanentPincode": {
        required: true
    },
    "user.correspondenceCity": {
        required: false
    },
    "user.correspondencePincode": {
        required: false
    },
    "user.correspondenceAddress": {
        required: false
    },
    "user.active": {
        required: true
    },
    "user.dob": {
        required: true
    },
    "user.locale": {
        required: false
    },
    "user.signature": {
        required: false
    },
    "user.fatherOrHusbandName": {
        required: false
    },
    "user.bloodGroup": {
        required: false
    },
    "user.identificationMark": {
        required: false
    },
    "user.photo": {
        required: false
    },
    "assignments.fromDate": {
        required: true
    },
    "assignments.toDate": {
        required: true
    },
    "assignments.fund": {
        required: false
    },
    "assignments.function": {
        required: false
    },
    "assignments.grade": {
        required: false
    },
    "assignments.designation": {
        required: true
    },
    "assignments.position": {
        required: true
    },
    "assignments.functionary": {
        required: false
    },
    "assignments.department": {
        required: true
    },
    "assignments.hod": {
        required: true
    },
    "assignments.mainDepartments": {
        required: true
    },
    "assignments.govtOrderNumber": {
        required: false
    },
    "assignments.is_primary": {
        required: true
    },
    "jurisdictions.jurisdictionsType": {
        required: true
    },
    "jurisdictions.boundary": {
        required: true
    },
    "education.qualification": {
        required: true
    },
    "education.majorSubject": {
        required: false
    },
    "education.yearOfPassing": {
        required: true
    },
    "education.university": {
        required: false
    },
    "education.documents": {
        required: false
    },
    "serviceHistory.id": {
        required: false
    },
    "serviceHistory.serviceInfo": {
        required: true
    },
    "serviceHistory.serviceFrom": {
        required: true
    },
    "serviceHistory.remarks": {
        required: false
    },
    "serviceHistory.orderNo": {
        required: false
    },
    "serviceHistory.documents": {
        required: false
    },
    "probation.designation": {
        required: true
    },
    "probation.declaredOn": {
        required: true
    },
    "probation.orderNo": {
        required: false
    },
    "probation.orderDate": {
        required: false
    },
    "probation.remarks": {
        required: false
    },
    "probation.documents": {
        required: false
    },
    "regularisation.designation": {
        required: true
    },
    "regularisation.declaredOn": {
        required: true
    },
    "regularisation.orderNo": {
        required: false
    },
    "regularisation.orderDate": {
        required: false
    },
    "regularisation.remarks": {
        required: false
    },
    "regularisation.documents": {
        required: false
    },
    "education.qualification": {
        required: true
    },
    "education.majorSubject": {
        required: false
    },
    "education.yearOfPassing": {
        required: true
    },
    "education.university": {
        required: false
    },
    "education.documents": {
        required: false
    },
    "technical.skill": {
        required: true
    },
    "technical.grade": {
        required: false
    },
    "technical.yearOfPassing": {
        required: false
    },
    "technical.remarks": {
        required: false
    },
    "technical.documents": {
        required: false
    },
    "test.test": {
        required: true
    },
    "test.yearOfPassing": {
        required: true
    },
    "test.remarks": {
        required: false
    },
    "test.documents": {
        required: false
    }

}

var assignmentDetailValidation = {
    fromDate: {
        required: true
    },
    toDate: {
        required: true
    },
    fund: {
        required: false
    },
    function: {
        required: false
    },
    grade: {
        required: false
    },
    designation: {
        required: true
    },
    position: {
        required: true
    },
    functionary: {
        required: false
    },
    department: {
        required: true
    },
    hod: {
        required: true
    },
    mainDepartments: {
        required: true
    },
    govtOrderNumber: {
        required: false
    },
    is_primary: {
        required: true
    }
};

var jurisdictions = {
    jurisdictionsType: {
        required: true
    },
    boundary: {
        required: true
    }
};

var serviceHistory = {
    id: {
        required: false
    },
    serviceInfo: {
        required: true
    },
    serviceFrom: {
        required: true
    },
    remarks: {
        required: false
    },
    orderNo: {
        required: false
    },
    documents: {
        required: false
    }
};

var probation = {
    designation: {
        required: true
    },
    declaredOn: {
        required: true
    },
    orderNo: {
        required: false
    },
    orderDate: {
        required: false
    },
    remarks: {
        required: false
    },
    documents: {
        required: false
    }
};

var regularisation = {
    designation: {
        required: true
    },
    declaredOn: {
        required: true
    },
    orderNo: {
        required: false
    },
    orderDate: {
        required: false
    },
    remarks: {
        required: false
    },
    documents: {
        required: false
    }
};

var education = {
    qualification: {
        required: true
    },
    majorSubject: {
        required: false
    },
    yearOfPassing: {
        required: true
    },
    university: {
        required: false
    },
    documents: {
        required: false
    }
};

var technical = {
    skill: {
        required: true
    },
    grade: {
        required: false
    },
    yearOfPassing: {
        required: false
    },
    remarks: {
        required: false
    },
    documents: {
        required: false
    }
}

var test = {
    test: {
        required: true
    },
    yearOfPassing: {
        required: true
    },
    remarks: {
        required: false
    },
    documents: {
        required: false
    }
};

var user = {
    userName: {
        required: true
    },
    name: {
        required: true
    },
    gender: {
        required: true
    },
    mobileNumber: {
        required: true

    },
    emailId: {
        required: false,

    },
    altContactNumber: {
        required: false
    },
    pan: {
        required: false
    },
    aadhaarNumber: {
        required: false

    },
    permanentAddress: {
        required: true
    },
    permanentCity: {
        required: true
    },
    permanentPincode: {
        required: true
    },
    correspondenceCity: {
        required: false
    },
    correspondencePincode: {
        required: false
    },
    correspondenceAddress: {
        required: false
    },
    active: {
        required: true
    },
    dob: {
        required: true
    },
    locale: {
        required: false
    },
    signature: {
        required: false
    },
    fatherOrHusbandName: {
        required: false
    },
    bloodGroup: {
        required: false
    },
    identificationMark: {
        required: false
    },
    photo: {
        required: false
    }
}

$("input[name='user.dob']").datepicker({
    format: 'dd/mm/yyyy',
    endDate: '-15y',
    autoclose: true
});

// $("input[name='user.dob']").val("");
// $("input[name='user.dob']").on("change", function(e) {
//       fillValueToObject(this);
//   });


$('#dateOfAppointment').datepicker({
    format: 'dd/mm/yyyy',
    autoclose: true
});

$('#dateOfAppointment').on("change", function(e) {
    fillValueToObject(this);
    $('#dateOfJoining').val(this.value);
    var date_received = $("#dateOfJoining").val();
    var date_completed = $("#dateOfAppointment").val();
    var dateParts = date_received.split("/");
    var newDateStr = dateParts[1] + "/" + dateParts[0] + "/ " + dateParts[2];
    var date1 = new Date(newDateStr);

    var dateParts = date_completed.split("/");
    var newDateStr = dateParts[1] + "/" + dateParts[0] + "/" + dateParts[2];
    var date2 = new Date(newDateStr);
    if (date1 < date2) {
        showError("Appointment date must be before Joining date");
        $("#dateOfAppointment").val("");
    }

});




$('#dateOfJoining').datepicker({
    format: 'dd/mm/yyyy',
    autoclose: true
});

$('#dateOfJoining').on("change", function(e) {
    fillValueToObject(this);
    var date_received = $("#dateOfJoining").val();
    var date_completed = $("#dateOfAppointment").val();
    var dateParts = date_received.split("/");
    var newDateStr = dateParts[1] + "/" + dateParts[0] + "/ " + dateParts[2];
    var date1 = new Date(newDateStr);

    var dateParts = date_completed.split("/");
    var newDateStr = dateParts[1] + "/" + dateParts[0] + "/" + dateParts[2];
    var date2 = new Date(newDateStr);
    if (date1 < date2) {
        showError("Appointment date must be before Joining date");
        $("#dateOfAppointment").val("");
    }

});

$('#dateOfJoining').datepicker({
    format: 'dd/mm/yyyy',
    autoclose: true
});

$('#dateOfJoining').on("change", function(e) {
    fillValueToObject(this);
    var date_received = $("#dateOfJoining").val();
    var date_completed = $("#dateOfRetirement").val();
    var dateParts = date_received.split("/");
    var newDateStr = dateParts[1] + "/" + dateParts[0] + "/ " + dateParts[2];
    var date1 = new Date(newDateStr);

    var dateParts = date_completed.split("/");
    var newDateStr = dateParts[1] + "/" + dateParts[0] + "/" + dateParts[2];
    var date2 = new Date(newDateStr);
    if (date1 >= date2) {
        showError("Date of retairement must be after Joining date");
        $("#dateOfRetirement").val("");
    }

});



$('#dateOfRetirement').datepicker({
    format: 'dd/mm/yyyy',
    autoclose: true
});

$('#dateOfRetirement').on("change", function(e) {
    fillValueToObject(this);
    var date_received = $("#dateOfJoining").val();
    var date_completed = $("#dateOfRetirement").val();
    var dateParts = date_received.split("/");
    var newDateStr = dateParts[1] + "/" + dateParts[0] + "/ " + dateParts[2];
    var date1 = new Date(newDateStr);

    var dateParts = date_completed.split("/");
    var newDateStr = dateParts[1] + "/" + dateParts[0] + "/" + dateParts[2];
    var date2 = new Date(newDateStr);
    if (date1 > date2) {
        showError("Retirement date must be after Joining date");
        $("#dateOfRetirement").val("");
    } else {}
});



$('#dateOfJoining').datepicker({
    format: 'dd/mm/yyyy',
    autoclose: true
});

$('#dateOfJoining').on("change", function(e) {
    fillValueToObject(this);
    var date_received = $("#dateOfJoining").val();
    var date_completed = $("#dateOfTermination").val();
    var dateParts = date_received.split("/");
    var newDateStr = dateParts[1] + "/" + dateParts[0] + "/ " + dateParts[2];
    var date1 = new Date(newDateStr);

    var dateParts = date_completed.split("/");
    var newDateStr = dateParts[1] + "/" + dateParts[0] + "/" + dateParts[2];
    var date2 = new Date(newDateStr);
    if (date1 >= date2) {
        showError("Date of Termination must be after Joining date");
        $("#dateOfTermination").val("");
    }

});


$('#dateOfTermination').datepicker({
    format: 'dd/mm/yyyy',
    autoclose: true
});

$('#dateOfTermination').on("change", function(e) {
    fillValueToObject(this);
    var date_received = $("#dateOfJoining").val();
    var date_completed = $("#dateOfTermination").val();
    var dateParts = date_received.split("/");
    var newDateStr = dateParts[1] + "/" + dateParts[0] + "/ " + dateParts[2];
    var date1 = new Date(newDateStr);

    var dateParts = date_completed.split("/");
    var newDateStr = dateParts[1] + "/" + dateParts[0] + "/" + dateParts[2];
    var date2 = new Date(newDateStr);
    if (date1 > date2) {
        showError("Termination date must be after Joining date");
        $("#dateOfTermination").val("");
    } else {}

});

$('#dateOfJoining').datepicker({
    format: 'dd/mm/yyyy',
    autoclose: true
});

$('#dateOfJoining').on("change", function(e) {
    fillValueToObject(this);
    var date_received = $("#dateOfJoining").val();
    var date_completed = $("#dateOfResignation").val();
    var dateParts = date_received.split("/");
    var newDateStr = dateParts[1] + "/" + dateParts[0] + "/ " + dateParts[2];
    var date1 = new Date(newDateStr);

    var dateParts = date_completed.split("/");
    var newDateStr = dateParts[1] + "/" + dateParts[0] + "/" + dateParts[2];
    var date2 = new Date(newDateStr);
    if (date1 >= date2) {
        showError("Date of Designation  must be after Joining date");
        $("#dateOfResignation").val("");
    }

});

$('#dateOfResignation').datepicker({
    format: 'dd/mm/yyyy',
    autoclose: true
});

$('#dateOfResignation').on("change", function(e) {
    fillValueToObject(this);
    var date_received = $("#dateOfJoining").val();
    var date_completed = $("#dateOfResignation").val();
    var dateParts = date_received.split("/");
    var newDateStr = dateParts[1] + "/" + dateParts[0] + "/ " + dateParts[2];
    var date1 = new Date(newDateStr);

    var dateParts = date_completed.split("/");
    var newDateStr = dateParts[1] + "/" + dateParts[0] + "/" + dateParts[2];
    var date2 = new Date(newDateStr);
    if (date1 > date2) {
        showError("Resignation date must be after Joining date");
        $("#dateOfResignation").val("");
    } else {}
});



$("input[name='assignments.fromDate']").datepicker({
    format: 'dd/mm/yyyy',
    autoclose: true
});

$("input[name='assignments.fromDate']").on("change", function(e) {
    fillValueToObject(this);
    var from = $("input[name='assignments.fromDate']").val();
    var to = $("input[name='assignments.toDate']").val();
    var dateParts = from.split("/");
    var newDateStr = dateParts[1] + "/" + dateParts[0] + "/ " + dateParts[2];
    var date1 = new Date(newDateStr);

    var dateParts = to.split("/");
    var newDateStr = dateParts[1] + "/" + dateParts[0] + "/" + dateParts[2];
    var date2 = new Date(newDateStr);
    if (date1 > date2) {
        howError("End date must be after From date");
        $("input[name='assignments.toDate']").val("");
    } else {}
});



$("input[name='assignments.toDate']").datepicker({
    format: 'dd/mm/yyyy',
    autoclose: true
});

$("input[name='assignments.toDate']").on("change", function(e) {
    fillValueToObject(this);
    var from = $("input[name='assignments.fromDate']").val();
    var to = $("input[name='assignments.toDate']").val();
    var dateParts = from.split("/");
    var newDateStr = dateParts[1] + "/" + dateParts[0] + "/ " + dateParts[2];
    var date1 = new Date(newDateStr);

    var dateParts = to.split("/");
    var newDateStr = dateParts[1] + "/" + dateParts[0] + "/" + dateParts[2];
    var date2 = new Date(newDateStr);
    if (date1 > date2) {
        showError("End date must be after From date");
        $("input[name='assignments.toDate']").val("");
    } else {}
});




$("input[name='serviceHistory.serviceFrom']").datepicker({
    format: 'dd/mm/yyyy',
    autoclose: true
});


$("input[name='probation.orderDate']").datepicker({
    format: 'dd/mm/yyyy',
    autoclose: true
});


$("input[name='probation.declaredOn']").datepicker({
    format: 'dd/mm/yyyy',
    autoclose: true
});
$("input[name='regularisation.orderDate']").datepicker({
    format: 'dd/mm/yyyy',
    autoclose: true
});

$("input[name='regularisation.declaredOn']").datepicker({
    format: 'dd/mm/yyyy',
    autoclose: true
});









var startDate;
$("#dateOfJoining").datepicker({
    timepicker: true,
    closeOnDateSelect: false,
    closeOnTimeSelect: true,
    initTime: true,
    format: 'dd/mm/yyyy',
    minDate: 0,
    onChangeDateTime: function(dp, $input) {
        startDate = $("#startdate").val();
    },
    autoclose: true
});
$("#dateOfRetirement").datepicker({
    timepicker: true,
    closeOnDateSelect: false,
    closeOnTimeSelect: true,
    initTime: true,
    format: 'dd/mm/yyyy',
    onClose: function(current_time, $input) {
        var endDate = $("#enddate").val();
        if (startDate > endDate) {
            alert('Please select correct date');
        }
    },
    autoclose: true
});



// $('.datepicker').datepicker({
//       format: 'dd/mm/yyyy'
//
// });
//
// $(".datepicker").on("change", function() {
//     // alert('hey');
//     fillValueToObject(this);
// });
// .on('changeDate', function (ev) {
//     $('#date-daily').change();
// });
//
// $('#date-daily').val('0000-00-00');
// $('#date-daily').change(function () {
//     console.log($('#date-daily').val());
// });

// $('#user\\.dob').on("change",function()
// {
//     fillValueToObject(this);
// })
//Getting data for user input
$("input").on("keyup change", function() {
    fillValueToObject(this);
});

//Getting data for user input
$("select").on("change", function() {
    // if (this.id == "jurisdictions.jurisdictionsType") return;
    // else {
    if (this.id == "bank") {
        commonObject["bankbranches"] = commonApiPost("egf-masters", "bankbranches", "_search", {
            tenantId,
            "bank.id": this.value
        }).responseJSON["bankBranches"] || [];
        $(`#bankBranch`).html(`<option value=''>Select</option>`)
        for (var i = 0; i < commonObject["bankbranches"].length; i++) {
            $(`#bankBranch`).append(`<option value='${commonObject["bankbranches"][i]['id']}'>${commonObject["bankbranches"][i]['name']}</option>`)
        }
    }
    if (this.id == "jurisdictions.jurisdictionsType") {
        commonObject["jurisdictions_boundary"] = commonApiPost("v1/location", "boundarys", "boundariesByBndryTypeNameAndHierarchyTypeName", {
            boundaryTypeName: this.value,
            hierarchyTypeName: "ADMINISTRATION"
        }).responseJSON["Boundary"] || [];
        if (this.value == "CITY") {
            commonObject["juridictionTypeForCity"] = commonObject["jurisdictions_boundary"];
        } else if (this.value == "WARD") {
            commonObject["juridictionTypeForWard"] = commonObject["jurisdictions_boundary"];

        } else {
            commonObject["juridictionTypeForZone"] = commonObject["jurisdictions_boundary"];

        }
        $(`#jurisdictions\\.boundary`).html(`<option value=''>Select</option>`)

        for (var i = 0; i < commonObject["jurisdictions_boundary"].length; i++) {
            $(`#jurisdictions\\.boundary`).append(`<option value='${commonObject["jurisdictions_boundary"][i]['id']}'>${commonObject["jurisdictions_boundary"][i]['name']}</option>`)
        }
        // return;
    }
    if (($("#assignments\\.department").val() != "" && $("#assignments\\.designation").val() != "") && (this.id == "assignments.department" || this.id == "assignments.designation")) {
        commonObject["assignments_position"] = commonApiPost("hr-masters", "positions", "_search", {
            tenantId,
            departmentId: $("#assignments\\.department").val(),
            designationId: $("#assignments\\.designation").val()
        }).responseJSON["Position"] || [];
        $(`#assignments\\.position`).html(`<option value=''>Select</option>`)

        for (var i = 0; i < commonObject["assignments_position"].length; i++) {
            $(`#assignments\\.position`).append(`<option value='${commonObject["assignments_position"][i]['id']}'>${commonObject["assignments_position"][i]['name']}</option>`)
        }
    }
    fillValueToObject(this);
    // }
});

//Getting data for user input
$("textarea").on("keyup change", function() {
    fillValueToObject(this);
})

//file change handle for file upload
/*$("input[type=file]").on("change", function(evt) {
    // console.log(this.value);
    // agreement[this.id] = this.value;
    var file = evt.currentTarget.files[0];

    //call post api update and update that url in pur agrement object
});*/

//initial setup
$("#departments").hide();

//it will split object string where it has .
function fillValueToObject(currentState) {
    if (currentState.id.includes(".")) {

        var splitResult = currentState.id.split(".");
        if (splitResult[0] === "user") {
            // if (currentState.id == "user.dob") {
            //     var dateSplit = currentState.value.split("-");
            //     var date = new Date(dateSplit[0], dateSplit[1], dateSplit[2]);
            //     var day = date.getDate().toString().length === 1 ? "0" + date.getDate() : date.getDate();
            //     var monthIn = date.getMonth().toString().length === 1 ? "0" + date.getMonth() : date.getMonth();
            //     var yearIn = date.getFullYear();
            //     employee[splitResult[0]][splitResult[1]] = day + "/" + monthIn + "/" + yearIn;
            //
            // } else
            if (currentState.id == "user.active") {
                employee[splitResult[0]][splitResult[1]] = currentState.value;

            } else if (currentState.type === "file") {
                employee[splitResult[0]][splitResult[1]] = $.extend(true, [], currentState.files);
            } else {
                employee[splitResult[0]][splitResult[1]] = currentState.value;
            }
        } else {
            // if (currentState.id == "assignments.fromDate" || currentState.id == "assignments.toDate" || currentState.id == "serviceHistory.serviceFrom" || currentState.id == "probation.orderDate" || currentState.id == "probation.declaredOn" || currentState.id == "regularisation.declaredOn" || currentState.id == "regularisation.orderDate" || currentState.id == "education.yearOfPassing" || currentState.id == "technical.yearOfPassing" || currentState.id == "test.yearOfPassing") {
            //     var dateSplit = currentState.value.split("-");
            //     var date = new Date(dateSplit[0], dateSplit[1], dateSplit[2]);
            //     var day = date.getDate().toString().length === 1 ? "0" + date.getDate() : date.getDate();
            //     var monthIn = date.getMonth().toString().length === 1 ? "0" + date.getMonth() : date.getMonth();
            //     var yearIn = date.getFullYear();
            //     employeeSubObject[splitResult[0]][splitResult[1]] = day + "/" + monthIn + "/" + yearIn;
            //
            // } else
            if (currentState.id == "assignments.mainDepartments") {
                tempListBox = [];
                for (var i = 0; i < $(currentState).val().length; i++) {
                    tempListBox.push({
                        department: $(currentState).val()[i]
                    })
                }


            }
            // else if(currentState.id=="assignments.isPrimary")
            // {
            //   employeeSubObject[splitResult[0]][splitResult[1]]=currentState.value=="No"?false:true;
            // }
            // if (currentState.id=="jurisdictions.boundary") {
            //       employeeSubObject[splitResult[0]][splitResult[1]].push(currentState.value);
            // }
            else if (currentState.type === "file") {
                employeeSubObject[splitResult[0]][splitResult[1]] = $.extend(true, [], currentState.files);
            } else
                employeeSubObject[splitResult[0]][splitResult[1]] = currentState.value;
        }

    } else {
        // if (currentState.id == "dateOfAppointment" || currentState.id == "dateOfRetirement" || currentState.id == "dateOfTermination" || currentState.id == "retirementAge" || currentState.id == "dateOfJoining" || currentState.id == "dateOfRetirement" || currentState.id == "dateOfTermination" || currentState.id == "dateOfResignation") {
        //     var dateSplit = currentState.value.split("-");
        //     var date = new Date(dateSplit[0], dateSplit[1], dateSplit[2]);
        //     var day = date.getDate().toString().length === 1 ? "0" + date.getDate() : date.getDate();
        //     var monthIn = date.getMonth().toString().length === 1 ? "0" + date.getMonth() : date.getMonth();
        //     var yearIn = date.getFullYear();
        //     employee[currentState.id] = day + "/" + monthIn + "/" + yearIn;
        // } else
        if (currentState.id == "languagesKnown") {
            employee[currentState.id] = $(currentState).val();
        } else if (currentState.type === "file") {
            employee[currentState.id] = $.extend(true, [], currentState.files);
        } else {
            employee[currentState.id] = currentState.value;
        }
    }
}

function clearModalInput(object, properties) {
    for (var variable in properties) {
        if (properties.hasOwnProperty(variable)) {
            $("#" + object + "\\." + variable).val(properties[variable]);
        }
    }
}

//need to cleat editIndex and temprory pbject
$('#assignmentDetailModal').on('hidden.bs.modal', function(e) {
    $('.error-p').hide();
    editIndex = -1;
    employeeSubObject["assignments"] = {
        fromDate: "",
        toDate: "",
        department: "",
        designation: "",
        position: "",
        isPrimary: false,
        fund: "",
        function: "",
        functionary: "",
        grade: "",
        hod: false,
        // mainDepartments: "",
        govtOrderNumber: "",
        documents:null
    };
    clearModalInput("assignments", employeeSubObject["assignments"]);
})

$('#jurisdictionDetailModal').on('hidden.bs.modal', function(e) {
    $('.error-p').hide();
    editIndex = -1;
    employeeSubObject["jurisdictions"] = {
        jurisdictionsType: "",
        boundary: ""
    }
    clearModalInput("jurisdictions", employeeSubObject["jurisdictions"]);
})

$('#serviceHistoryDetailModal').on('hidden.bs.modal', function(e) {
    $('.error-p').hide();
    editIndex = -1;
    employeeSubObject["serviceHistory"] = {
        id: employee["serviceHistory"].length + 1,
        serviceInfo: "",
        serviceFrom: "",
        remarks: "",
        orderNo: "",
        documents: null
    }
    clearModalInput("serviceHistory", employeeSubObject["serviceHistory"]);
})

$('#probationDetailModal').on('hidden.bs.modal', function(e) {
    $('.error-p').hide();
    editIndex = -1;
    employeeSubObject["probation"] = {
        designation: "",
        declaredOn: "",
        orderNo: "",
        orderDate: "",
        remarks: "",
        documents: null
    }
    clearModalInput("probation", employeeSubObject["probation"]);

})

$('#regularisationDetailModal').on('hidden.bs.modal', function(e) {
    $('.error-p').hide();
    editIndex = -1;
    employeeSubObject["regularisation"] = {
        designation: "",
        declaredOn: "",
        orderNo: "",
        orderDate: "",
        remarks: "",
        documents: null
    }
    clearModalInput("regularisation", employeeSubObject["regularisation"]);

})

$('#technicalDetailModal').on('hidden.bs.modal', function(e) {
    $('.error-p').hide();
    editIndex = -1;
    employeeSubObject["technical"] = {
        skill: "",
        grade: "",
        yearOfPassing: "",
        remarks: "",
        documents: null
    }
    clearModalInput("technical", employeeSubObject["technical"]);

})

$('#educationDetailModal').on('hidden.bs.modal', function(e) {
    $('.error-p').hide();
    editIndex = -1;
    employeeSubObject["education"] = {
        qualification: "",
        majorSubject: "",
        yearOfPassing: "",
        university: "",
        documents: null
    }
    clearModalInput("education", employeeSubObject["education"]);

})

$('#testDetailModal').on('hidden.bs.modal', function(e) {
    $('.error-p').hide();
    editIndex = -1;
    employeeSubObject["test"] = {
        test: "",
        yearOfPassing: "",
        remarks: "",
        documents: null
    }
    clearModalInput("test", employeeSubObject["test"]);

})


function enableAndDisable(id) {
    $(`#${id}`).toggle();
}

//common update
function updateTable(tableName, modalName, object) {
    $(tableName).html(``);
    for (var i = 0; i < employee[object].length; i++) {
        $(tableName).append(`<tr>`);
        for (var key in employee[object][i]) {
            if (key === "department" || key === "designation" || key === "position" || key === "fund" || key === "function" || key === "functionary" || (object == "assignments" && key === "grade") || key === "mainDepartments" || key === "jurisdictionsType") {
                $(tableName).append(`<td data-label=${key}>
                                  ${getNameById(key,employee[object][i][key],"")}
                            </td>`)
            } else if (key === "boundary") {
                $(tableName).append(`<td data-label=${key}>
                                ${getNameById(key,employee[object][i][key],employee[object][i]["jurisdictionsType"])}
                          </td>`)
            } else if (key == "hod") {
                $(tableName).append(`<td data-label=${key}>
                                ${employee[object][i][key].length>0?"Yes":"No"}
                          </td>`)
            } else {
                if (key=="documents") {
                    // var name="";
                    // for (var i = 0; i < employee[object][i][key].length; i++) {
                    //   name=name +" "+ employee[object][i][key][i]["name"];
                    // }
                    $(tableName).append(`<td data-label=${key}>

                                      ${employee[object][i][key]?employee[object][i][key].length:""}
                                </td>`)
                } else {
                  $(tableName).append(`<td data-label=${key}>

                                    ${employee[object][i][key]}
                              </td>`)
                }

            }

        }
        $(tableName).append(`<td data-label="Action">
                    <button onclick="markEditIndex(${i},'${modalName}','${object}')" class="btn btn-default btn-action"><span class="glyphicon glyphicon-pencil"></span></button>
                    <button onclick="commonDeleteFunction('${tableName}','${modalName}','${object}',${i})" class="btn btn-default btn-action"><span class="glyphicon glyphicon-trash"></span></button>
                  </td>`);
        $(tableName).append(`</tr>`);
    }
}

//common edit mark index
function markEditIndex(index = -1, modalName = "", object = "") {
    editIndex = index;
    $('#' + modalName).modal('show');
    //assignments  details modal when it edit modalName
    $('#' + modalName).on('shown.bs.modal', function(e) {
        if (editIndex != -1) {
            employeeSubObject[object] = Object.assign({}, employee[object][editIndex]);

            for (var key in employeeSubObject[object]) {
                // if($(`#${object}\\.${key}`).length == 0) {
                //       alert("not there");
                //   }
                // if (object + "." + key == "assignments.fromDate" || object + "." + key == "assignments.toDate" || object + "." + key == "serviceHistory.serviceFrom" || object + "." + key == "probation.orderDate" || object + "." + key == "probation.declaredOn" || object + "." + key == "regularisation.declaredOn" || object + "." + key == "regularisation.orderDate" || object + "." + key == "education.yearOfPassing" || object + "." + key == "technical.yearOfPassing" || object + "." + key == "test.yearOfPassing") {
                //     var dateSplit = employeeSubObject[object][key].split("/");
                //     var date = new Date(dateSplit[0], dateSplit[1], dateSplit[2]);
                //     // var day=date.getDate().toString().length===1?"0"+date.getDate():date.getDate();
                //     // var monthIn=date.getMonth().toString().length===1?"0"+date.getMonth():date.getMonth();
                //     // var yearIn=date.getFullYear();
                //     // employeeSubObject[splitResult[0]][splitResult[1]] = day+"/"+monthIn+"/"+yearIn;
                //     $(`#${object}\\.${key}`).val(date);
                //
                // } else {
                //     $(`#${object}\\.${key}`).val(employeeSubObject[object][key]);
                // }

                $(`#${object}\\.${key}`).val(employeeSubObject[object][key]);

            }
        }
    })
    // alert("fired");
}

//common add and update
function commonAddAndUpdate(tableName, modalName, object) {
    // if(switchValidation(object))
    if ($("#createEmployeeForm").valid()) {
        if (checkIfNoDup(employee, object, employeeSubObject[object])) {
            if (editIndex != -1) {
                employee[object][editIndex] = employeeSubObject[object];
                updateTable("#" + tableName, modalName, object);
            } else {
                if (object == "assignments") {
                    employeeSubObject[object]["hod"] = [];
                    if (tempListBox.length > 0) {
                        employeeSubObject[object]["hod"].push(tempListBox);
                    }
                    employee[object].push(Object.assign({}, employeeSubObject[object]));
                    updateTable("#" + tableName, modalName, object);
                } else {
                    employee[object].push(Object.assign({}, employeeSubObject[object]));
                    updateTable("#" + tableName, modalName, object);
                }

            }
            $(`#${modalName}`).modal("hide");
        } else {
            $(".error-p").show();
        }
    } else {
        return;
    }
}

//common Delete
function commonDeleteFunction(tableName, modalName, object, index) {
    employee[object].splice(index, 1);
    updateTable(tableName, modalName, object);
}

//show reslut event
// $("#showResultEvent").on("click",function() {
//     // $("#showResult").text(employee);
//     console.log(employee);
// })





//
final_validatin_rules = Object.assign(validation_rules, commom_fields_rules);
for (var key in final_validatin_rules) {
    if (final_validatin_rules[key].required) {
        // console.log(key.split("."));
        if (key.split(".").length == 1) {
            $(`label[for=${key}]`).append(`<span> *</span>`);

        }
    }
    // $(`#${key}`).attr("disabled",true);
};

$.validator.addMethod('phone', function(value) {
    return /^[0-9]{10}$/.test(value);
}, 'Please enter a valid phone number.');

$.validator.addMethod('aadhar', function(value) {
    return /^[0-9]{12}$/.test(value);
}, 'Please enter a valid aadhar.');

$.validator.addMethod('panNo', function(value) {
    return /^[0-9a-zA-Z]{10}$/.test(value);
}, 'Please enter a valid pan.');

$("#addEmployee").on("click", function(e) {
    e.preventDefault();
    $("#createEmployeeForm").submit();
    // switchValidation("final_validatin_rules");
})








function addMandatoryStart(validationObject, prefix = "") {
    for (var key in validationObject) {
        if (prefix === "") {
            if (validationObject[key].required) {
                $(`label[for=${key}]`).append(`<span> *</span>`);
            }
        } else {
            if (validationObject[key].required) {
                $(`label[for=${prefix}\\.${key}]`).append(`<span> *</span>`);
            }
        }

        // $(`#${key}`).attr("disabled",true);
    };
}

addMandatoryStart(assignmentDetailValidation, "assignments");

addMandatoryStart(jurisdictions, "jurisdictions");

addMandatoryStart(serviceHistory, "serviceHistory");

addMandatoryStart(probation, "probation");

addMandatoryStart(regularisation, "regularisation");

addMandatoryStart(education, "education");

addMandatoryStart(test, "test");

addMandatoryStart(technical, "technical");

addMandatoryStart(user, "user");

// function switchValidation(whichObject) {
//     switch (whichObject) {
//         case "final_validatin_rules":
//             // removeRule({assignments,jurisdictions,serviceHistory,probation,regularisation,education,test,technical});
//             addRule(final_validatin_rules);
//             $("#createEmployeeForm").submit();
//         case "assignments":
//             // removeRule({final_validatin_rules,jurisdictions,serviceHistory,probation,regularisation,education,test,technical});
//             addRule(assignmentDetailValidation, "assignments");
//             return $("#createEmployeeForm").valid();
//         case "jurisdictions":
//             // removeRule({final_validatin_rules,jurisdictions,serviceHistory,probation,regularisation,education,test,technical});
//             addRule(jurisdictions, "jurisdictions");
//             return $("#createEmployeeForm").valid();
//         case "serviceHistory":
//             // removeRule({final_validatin_rules,jurisdictions,serviceHistory,probation,regularisation,education,test,technical});
//             addRule(serviceHistory, "serviceHistory");
//             return $("#createEmployeeForm").valid();
//         case "probation":
//             // removeRule({final_validatin_rules,jurisdictions,serviceHistory,probation,regularisation,education,test,technical});
//             addRule(probation, "probation");
//             return $("#createEmployeeForm").valid();
//         case "regularisation":
//             // removeRule({final_validatin_rules,jurisdictions,serviceHistory,probation,regularisation,education,test,technical});
//             addRule(regularisation, "regularisation");
//             return $("#createEmployeeForm").valid();
//         case "education":
//             // removeRule({final_validatin_rules,jurisdictions,serviceHistory,probation,regularisation,education,test,technical});
//             addRule(education, "education")
//             return $("#createEmployeeForm").valid();
//         case "technical":
//             // removeRule({final_validatin_rules,jurisdictions,serviceHistory,probation,regularisation,education,test,technical});
//             addRule(technical, "technical");
//             return $("#createEmployeeForm").valid();
//         case "test":
//             // removeRule({final_validatin_rules,jurisdictions,serviceHistory,probation,regularisation,education,test,technical});
//             addRule(test, "test");
//             return $("#createEmployeeForm").valid();
//         default:
//
//     }
// }

// function removeRule(arrayOfObject) {
//     // console.log(arrayOfObject);
//     for (var item in arrayOfObject) {
//         if (item === "final_validatin_rules") {
//             for (var itemInner in arrayOfObject[item]) {
//
//                 $(`#${itemInner}`).rules("remove");
//
//             }
//         } else {
//             for (var itemInner in arrayOfObject[item]) {
//
//                 $(`#${item}\\.${itemInner}`).rules("remove");
//
//             }
//         }
//     }
//
// }
// function addRule(object, name) {
//     // console.log(object);
//     for (var item in object) {
//         if (object[item].required) {
//             $(`#${name}\\.${item}`).rules("add", object[item]);
//         }
//     }
// }

function isHavingPrimary() {
    for (var i = 0; i < employee.assignments.length; i++) {
      if (employee.assignments[i].isPrimary) {
          return true;
      }

    }
    return false;
}


// Adding Jquery validation dynamically
$("#createEmployeeForm").validate({
    rules: final_validatin_rules,
    submitHandler: function(form) {
        // console.log(form);
        if (!hasAllRequiredFields(employee)) {
            showError("Please enter all mandatory fields.");
        } else if ((employee.assignments.length > 0 && isHavingPrimary()) && employee.jurisdictions.length > 0) {
            //Call api

            var empJuridictiona = employee["jurisdictions"];
            employee["jurisdictions"] = [];
            for (var i = 0; i < empJuridictiona.length; i++) {
                employee["jurisdictions"].push(empJuridictiona[i].boundary);
            }
            //Upload files if any
            uploadFiles(employee, function(err, emp) {
                if (err) {
                    //Handle error
                } else {
                    var response = $.ajax({
                        url: baseUrl + "/hr-employee/employees/_create?tenantId=1",
                        type: 'POST',
                        dataType: 'json',
                        data: JSON.stringify({
                            RequestInfo: requestInfo,
                            Employee: emp
                        }),
                        async: false,
                        headers: {
                            'auth-token': authToken
                        },
                        contentType: 'application/json'
                    });

                    if (response["status"] === 200) {
                        showSuccess("Employee added successfully.");
                        // window.location.href="../../../../app/hr/common/employee-attendance.html";
                    } else {
                        alert(response["statusText"]);
                    }


                    // $.post(`${baseUrl}hr-employee/employees/_create?tenantId=1`, {
                    //     RequestInfo: requestInfo,
                    //     Employee: employee
                    // }, function(response) {
                    //     alert("submit");
                    //     // window.open("../../../../app/search-assets/create-agreement-ack.html?&agreement_id=aeiou", "", "width=1200,height=800")
                    //     console.log(response);
                    // },function(error){
                    //   alert("error")
                    //   console.log(error);
                    // })
                }
            })
        } else {
            showError("Please enter atleast one assignment and jurisdiction.");
        }
        //alert("submitterd");
        // form.submit();

        // console.log(agreement);



    }
})

function uploadFiles(employee, cb) {
    if (employee.user.photo && typeof employee.user.photo == "object") {
        makeAjaxUpload(employee.user.photo[0], function(err, res) {
            if (err) {
                cb(err);
            } else {
                employee.user.photo = `/filestore/v1/files/id?fileStoreId=${res.files[0].fileStoreId}`;
                uploadFiles(employee, cb);
            }
        })
    } else if (employee.user.signature && typeof employee.user.signature == "object") {
        makeAjaxUpload(employee.user.signature[0], function(err, res) {
            if (err) {
                cb(err);
            } else {
                employee.user.signature = `/filestore/v1/files/id?fileStoreId=${res.files[0].fileStoreId}`;
                uploadFiles(employee, cb);
            }
        })
    } else if (employee.documents && employee.documents.length && employee.documents[0].constructor == File) {
        let counter = employee.documents.length,
            breakout = 0;
        for (let i = 0; len = employee.documents.length, i < len; i++) {
            makeAjaxUpload(employee.documents[i], function(err, res) {
                if (breakout == 1)
                    return;
                else if (err) {
                    cb(err);
                    breakout = 1;
                } else {
                    counter--;
                    employee.documents[i] = `/filestore/v1/files/id?fileStoreId=${res.files[0].fileStoreId}`;
                    if (counter == 0 && breakout == 0)
                        uploadFiles(employee, cb);
                }
            })
        }
    } else if (employee.assignments.length && hasFile(employee.assignments)) {
        let counter1 = employee.assignments.length,
            breakout = 0;
        for (let i = 0; len = employee.assignments.length, i < len; i++) {
            let counter = employee.assignments[i].documents.length;
            for (let j = 0; len1 = employee.assignments[i].documents.length, j < len1; j++) {
                makeAjaxUpload(employee.assignments[i].documents[j], function(err, res) {
                    if (breakout == 1)
                        return;
                    else if (err) {
                        cb(err);
                        breakout = 1;
                    } else {
                        counter--;
                        employee.assignments[i].documents[j] = `/filestore/v1/files/id?fileStoreId=${res.files[0].fileStoreId}`;
                        if (counter == 0 && breakout == 0) {
                            counter1--;
                            if (counter1 == 0 && breakout == 0)
                                uploadFiles(employee, cb);
                        }
                    }
                })
            }
        }
    } else if (employee.serviceHistory.length && hasFile(employee.serviceHistory)) {
        let counter1 = employee.serviceHistory.length,
            breakout = 0;
        for (let i = 0; len = employee.serviceHistory.length, i < len; i++) {
            let counter = employee.serviceHistory[i].documents.length;
            for (let j = 0; len1 = employee.serviceHistory[i].documents.length, j < len1; j++) {
                makeAjaxUpload(employee.serviceHistory[i].documents[j], function(err, res) {
                    if (breakout == 1)
                        return;
                    else if (err) {
                        cb(err);
                        breakout = 1;
                    } else {
                        counter--;
                        employee.serviceHistory[i].documents[j] = `/filestore/v1/files/id?fileStoreId=${res.files[0].fileStoreId}`;
                        if (counter == 0 && breakout == 0) {
                            counter1--;
                            if (counter1 == 0 && breakout == 0)
                                uploadFiles(employee, cb);
                        }
                    }
                })
            }
        }
    } else if (employee.probation.length && hasFile(employee.probation)) {
        let counter1 = employee.probation.length,
            breakout = 0;
        for (let i = 0; len = employee.probation.length, i < len; i++) {
            let counter = employee.probation[i].documents.length;
            for (let j = 0; len1 = employee.probation[i].documents.length, j < len1; j++) {
                makeAjaxUpload(employee.probation[i].documents[j], function(err, res) {
                    if (breakout == 1)
                        return;
                    else if (err) {
                        cb(err);
                        breakout = 1;
                    } else {
                        counter--;
                        employee.probation[i].documents[j] = `/filestore/v1/files/id?fileStoreId=${res.files[0].fileStoreId}`;
                        if (counter == 0 && breakout == 0) {
                            counter1--;
                            if (counter1 == 0 && breakout == 0)
                                uploadFiles(employee, cb);
                        }
                    }
                })
            }
        }
    } else if (employee.regularisation.length && hasFile(employee.regularisation)) {
        let counter1 = employee.regularisation.length,
            breakout = 0;
        for (let i = 0; len = employee.regularisation.length, i < len; i++) {
            let counter = employee.regularisation[i].documents.length;
            for (let j = 0; len1 = employee.regularisation[i].documents.length, j < len1; j++) {
                makeAjaxUpload(employee.regularisation[i].documents[j], function(err, res) {
                    if (breakout == 1)
                        return;
                    else if (err) {
                        cb(err);
                        breakout = 1;
                    } else {
                        counter--;
                        employee.regularisation[i].documents[j] = `/filestore/v1/files/id?fileStoreId=${res.files[0].fileStoreId}`;
                        if (counter == 0 && breakout == 0) {
                            counter1--;
                            if (counter1 == 0 && breakout == 0)
                                uploadFiles(employee, cb);
                        }
                    }
                })
            }
        }
    } else if (employee.technical.length && hasFile(employee.technical)) {
        let counter1 = employee.technical.length,
            breakout = 0;
        for (let i = 0; len = employee.technical.length, i < len; i++) {
            let counter = employee.technical[i].documents.length;
            for (let j = 0; len1 = employee.technical[i].documents.length, j < len1; j++) {
                makeAjaxUpload(employee.technical[i].documents[j], function(err, res) {
                    if (breakout == 1)
                        return;
                    else if (err) {
                        cb(err);
                        breakout = 1;
                    } else {
                        counter--;
                        employee.technical[i].documents[j] = `/filestore/v1/files/id?fileStoreId=${res.files[0].fileStoreId}`;
                        if (counter == 0 && breakout == 0) {
                            counter1--;
                            if (counter1 == 0 && breakout == 0)
                                uploadFiles(employee, cb);
                        }
                    }
                })
            }
        }
    } else if (employee.education.length && hasFile(employee.education)) {
        let counter1 = employee.education.length,
            breakout = 0;
        for (let i = 0; len = employee.education.length, i < len; i++) {
            let counter = employee.education[i].documents.length;
            for (let j = 0; len1 = employee.education[i].documents.length, j < len1; j++) {
                makeAjaxUpload(employee.education[i].documents[j], function(err, res) {
                    if (breakout == 1)
                        return;
                    else if (err) {
                        cb(err);
                        breakout = 1;
                    } else {
                        counter--;
                        employee.education[i].documents[j] = `/filestore/v1/files/id?fileStoreId=${res.files[0].fileStoreId}`;
                        if (counter == 0 && breakout == 0) {
                            counter1--;
                            if (counter1 == 0 && breakout == 0)
                                uploadFiles(employee, cb);
                        }
                    }
                })
            }
        }
    } else if (employee.test.length && hasFile(employee.test)) {
        let counter1 = employee.test.length,
            breakout = 0;
        for (let i = 0; len = employee.test.length, i < len; i++) {
            let counter = employee.test[i].documents.length;
            for (let j = 0; len1 = employee.test[i].documents.length, j < len1; j++) {
                makeAjaxUpload(employee.test[i].documents[j], function(err, res) {
                    if (breakout == 1)
                        return;
                    else if (err) {
                        cb(err);
                        breakout = 1;
                    } else {
                        counter--;
                        employee.test[i].documents[j] = `/filestore/v1/files/id?fileStoreId=${res.files[0].fileStoreId}`;
                        if (counter == 0 && breakout == 0) {
                            counter1--;
                            if (counter1 == 0 && breakout == 0)
                                uploadFiles(employee, cb);
                        }
                    }
                })
            }
        }
    } else {
        cb(null, employee);
    }
}

function hasFile(elements) {
    if (elements && elements.constructor == Array) {
        for (var i = 0; i < elements.length; i++) {
            if (elements[i].documents && elements[i].documents.constructor == Array)
                for (var j = 0; j < elements[i].documents.length; j++)
                    if (elements[i].documents[j].constructor == File)
                        return true;
        }
    }
    return false;
}

function makeAjaxUpload(file, cb) {
    let formData = new FormData();
    formData.append("jurisdictionId", "ap.public");
    formData.append("module", "PGR");
    formData.append("file", file);
    $.ajax({
        url: baseUrl + "/filestore/v1/files",
        data: formData,
        cache: false,
        contentType: false,
        processData: false,
        type: 'POST',
        success: function(res) {
            cb(null, res);
        },
        error: function(jqXHR, exception) {
            cb(jqXHR.responseText || jqXHR.statusText);
        }
    });
}

function hasAllRequiredFields(emp) {
    return (emp.user.name && emp.code && emp.employeeType && emp.employeeStatus && emp.user.dob && emp.user.gender && emp.maritalStatus && emp.user.userName && emp.user.mobileNumber && emp.user.permanentAddress && emp.user.permanentCity && emp.user.permanentPincode && emp.dateOfAppointment);
}

function showError(msg) {
    $('#error-alert-span').text(msg);
    $('#error-alert-div').show();
    setTimeout(function() {
        $('#error-alert-div').hide();
    }, 3000);
}

function showSuccess(msg) {
    $('#success-alert-span').text(msg);
    $('#success-alert-div').show();
    setTimeout(function() {
        $('#success-alert-div').hide();
    }, 3000);
}

function checkIfNoDup(employee, objectType, subObject) {
    if (employee[objectType].length === 0)
        return true;
    else if (objectType === "assignments") {
        for (let i = 0; i < employee[objectType].length; i++) {
            if (employee[objectType][i].fromDate == subObject.fromDate || employee[objectType][i].toDate == subObject.toDate)
                return false;
        }
    } else if (objectType == "jurisdictions") {
        for (let i = 0; i < employee[objectType].length; i++) {
            if (employee[objectType][i].jurisdictionsType == subObject.jurisdictionsType || employee[objectType][i].boundary == subObject.boundary)
                return false;
        }
    }

    return true;
}
