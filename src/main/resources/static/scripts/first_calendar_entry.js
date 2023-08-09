//console.log(localStorage.getItem('plantId'));
// Call the fetch and populate function when the document is loaded
document.addEventListener('DOMContentLoaded', () => {
    let plantId = localStorage.getItem('plantId');
    fetchAndPopulateVarieties(plantId);
});

// Event listener for the new variety checkbox to toggle the visibility of new variety fields
document.querySelector("#newVarietyCheckbox").addEventListener('change', (event) => {
    let newVarietyDiv = document.querySelector("#newVarietyDiv");
    if (event.target.checked) {
        newVarietyDiv.style.display = "block";
        // Deselect all radio buttons
        let radioButtons = document.querySelectorAll('input[name="variety"]');
        radioButtons.forEach(rb => rb.checked = false);
    } else {
        newVarietyDiv.style.display = "none";
    }
});

// Deselect the checkbox when a radio button is selected
document.querySelector("#varietyRadio").addEventListener('change', (event) => {
    if (event.target.type === "radio") {
        document.querySelector("#newVarietyCheckbox").checked = false;
        document.querySelector("#newVarietyDiv").style.display = "none";
    }
});

// Fetch available varieties and populate them as radio buttons
async function fetchAndPopulateVarieties(plantId) {
    let response = await fetch(`/variety/plant/${plantId}`, { credentials: 'include' });
    console.log("Response Status: ", response.status);
    let varieties = await response.json();
    console.log("Varieties: ", varieties);

    let varietyRadioDiv = document.querySelector("#varietyRadio");

    varieties.forEach(variety => {
        let containerDiv = document.createElement('div');
        containerDiv.className = 'variety-container';

        let radioInput = document.createElement('input');
        radioInput.type = 'radio';
        radioInput.name = 'variety';
        radioInput.value = variety.varietyId;
        radioInput.id = `variety-${variety.varietyId}`;

        let label = document.createElement('label');
        label.textContent = variety.varietyName;
        label.htmlFor = radioInput.id;

        containerDiv.appendChild(radioInput);
        containerDiv.appendChild(label);

        varietyRadioDiv.appendChild(containerDiv);
    });
}

document.querySelector("#firstEntryForm").addEventListener("submit", async (event) => {
    event.preventDefault();

    let plantId = localStorage.getItem('plantId');
    let newVarietyName = document.querySelector("#newVarietyName").value;
    let newVarietyDescription = document.querySelector("#newVarietyDescription").value;
    let selectedVarietyRadio = document.querySelector('input[name="variety"]:checked');
    let selectedVarietyId = selectedVarietyRadio ? selectedVarietyRadio.value : null;

    let informationBox = document.querySelector("#informationBox");
    informationBox.textContent = "";  // Clear previous message

    // Check if new variety is being created
    if (document.querySelector("#newVarietyCheckbox").checked) {
        if (newVarietyName == "" || newVarietyDescription == "") {
            informationBox.textContent = "New Variety Name and Description are required.";
            return;
        }
        let response = await createNewVariety(plantId, newVarietyName, newVarietyDescription);
        if (!response.ok) {
            informationBox.textContent = "Error creating new variety. Please try again.";
            return;
        }
        selectedVarietyId = await response.json();
    }
    else if (selectedVarietyId == null) {
        informationBox.textContent = "Please select or create a new variety.";
        return;
    }
});



function createNewVariety(plantId, newVarietyName, newVarietyDescription) {
    return fetch(`/variety/createVariety`, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        credentials: 'include',
        body: JSON.stringify({
            variety: {
                varietyName: newVarietyName,
                varietyDescription: newVarietyDescription
            },
            plantId: plantId
        })
    });
}


