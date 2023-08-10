document.addEventListener("DOMContentLoaded", function() {

    // Date validation
    document.getElementById('date').addEventListener('input', function() {
        const today = new Date();
        const sixMonthsEarlier = new Date(today);
        sixMonthsEarlier.setMonth(today.getMonth() - 6);

        this.max = today.toISOString().split('T')[0];
        this.min = sixMonthsEarlier.toISOString().split('T')[0];

        const dateValue = this.value;
        const dateError = document.getElementById('dateError');

        if (!dateValue) {
            dateError.textContent = "Date is required.";
            dateError.style.display = "block";
        } else {
            dateError.style.display = "none";
        }
    });

    // Day Temperature validation
        document.getElementById('dayTemp').addEventListener('input', function() {
            const dayTemp = this.value;
            const dayTempError = document.getElementById('dayTempError');

            if (dayTemp && (dayTemp > 50 || dayTemp < -50)) {
                dayTempError.textContent = "Day Temperature should be between -50 and 50.";
                dayTempError.style.display = "block";
            } else {
                dayTempError.style.display = "none";
            }
        });

        // Night Temperature validation
        document.getElementById('nightTemp').addEventListener('input', function() {
            const nightTemp = this.value;
            const nightTempError = document.getElementById('nightTempError');

            if (nightTemp && (nightTemp > 50 || nightTemp < -50)) {
                nightTempError.textContent = "Night Temperature should be between -50 and 50.";
                nightTempError.style.display = "block";
            } else {
                nightTempError.style.display = "none";
            }
        });

    // Common function to validate positive values
    function validatePositiveValue(elementId, errorMessage) {
        const element = document.getElementById(elementId);
        const errorElement = document.getElementById(elementId + "Error");

        element.addEventListener('input', function() {
            const value = this.value;

            if (value < 0) {
                errorElement.textContent = errorMessage;
                errorElement.style.display = "block";
            } else {
                errorElement.style.display = "none";
            }
        });
    }

    validatePositiveValue('waterAmount', "Water Amount should be a positive value.");
    validatePositiveValue('nutrientAmount', "Nutrient Amount should be a positive value.");
    validatePositiveValue('height', "Height should be a positive value.");
    validatePositiveValue('width', "Width should be a positive value.");

    const growthStages = document.getElementsByName('growthStage');
    for (let i = 0; i < growthStages.length; i++) {
        growthStages[i].addEventListener('change', function() {
            const growthStageError = document.getElementById('growthStageError');

            if (Array.from(growthStages).some(radio => radio.checked)) {
                growthStageError.style.display = "none";
            } else {
                growthStageError.textContent = "Please select a growth stage.";
                growthStageError.style.display = "block";
            }
            const harvestedCheckbox = document.getElementById('harvestedDiv');

            if (document.getElementById('ripening').checked) {
                harvestedCheckbox.style.display = "block";
            } else {
                harvestedCheckbox.style.display = "none";
                harvestedCheckbox.checked = false;
            }
        });
    }

    // Common function to validate values between 1 and 10
    function validateValueBetweenOneAndTen(elementId, errorMessage) {
        const element = document.getElementById(elementId);
        const errorElement = document.getElementById(elementId + "Error");

        element.addEventListener('input', function() {
            const value = this.value;

            if (value < 1 || value > 10) {
                errorElement.textContent = errorMessage;
                errorElement.style.display = "block";
            } else {
                errorElement.style.display = "none";
            }
        });
    }

    validateValueBetweenOneAndTen('pestImpact', "Pest Impact should be between 1 and 10.");
    validateValueBetweenOneAndTen('diseaseImpact', "Disease Impact should be between 1 and 10.");
    validateValueBetweenOneAndTen('lightAmount', "Light Amount should be between 1 and 10.");

    // Function to toggle the visibility of a related input based on a checkbox
    function toggleInputBasedOnCheckbox(checkboxId, inputId) {
        const checkbox = document.getElementById(checkboxId);
        const relatedInput = document.getElementById(inputId);

        // Initial state
        if (checkbox.checked) {
            relatedInput.parentElement.style.display = "block";
        } else {
            relatedInput.parentElement.style.display = "none";
        }

        checkbox.addEventListener('change', function() {
            if (this.checked) {
                relatedInput.parentElement.style.display = "block";
            } else {
                relatedInput.parentElement.style.display = "none";
            }
        });
    }

    toggleInputBasedOnCheckbox('pestIssues', 'pestImpact');
    toggleInputBasedOnCheckbox('diseaseIssues', 'diseaseImpact');
    toggleInputBasedOnCheckbox('harvested', 'yield');
});

