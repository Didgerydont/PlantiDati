// Registration form management
document.getElementById('registerForm').addEventListener('submit', function(event) {
    // Prevent the form from being submitted normally
    event.preventDefault();

    // Check if passwords match
    if (!validatePasswords()) {
        return;
    }

    // Create a new user object based on the form fields
    var newUser = {
        username: document.getElementById('username').value,
        email: document.getElementById('email').value,
        password: document.getElementById('password').value,
        location: document.getElementById('location').value,
        profileCaption: document.getElementById('profileCaption').value
    };

    // Send the user data to the server
    fetch('/auth/register', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json'
        },
        body: JSON.stringify(newUser)
    })
    .then(function(response) {
        if (response.ok) {
            return response.json();
        } else {
            throw new Error('Error: ' + response.statusText);
        }
    })
    .then(function(data) {
        console.log('Registration Success:', data);

        // Show a success message
        var successMessage = document.createElement('div');
        successMessage.textContent = "Registration successful! Redirecting to login...";
        successMessage.style.color = 'green';
        document.body.appendChild(successMessage);

        // Introduce a delay before redirecting
        return new Promise(resolve => setTimeout(resolve, 1000));
    })
    .then(function() {
        // Redirect the user to the login page
        window.location.href = "/login";
    })
    .catch(function(error) {
        // Log the error for debugging
        console.error('Error:', error);

        // Show a generic error message to the user
        document.getElementById('usernameError').textContent = 'An error occurred. Please try again later.';
        document.getElementById('usernameError').style.display = 'block';
    });
});

// Check if user name has been taken
var usernameCheckTimeout;

document.getElementById('username').addEventListener('input', function(event) {
    clearTimeout(usernameCheckTimeout);  // Clear the timeout if it's been set

    var username = event.target.value;

    // Set a new timeout to perform the username check after the user stops typing for 500 ms
    usernameCheckTimeout = setTimeout(function() {
        fetch('/auth/isusernametaken?username=' + username)
            .then(function(response) {
                if (response.ok) {
                    return response.json();
                } else {
                    throw new Error('Error: ' + response.statusText);
                }
            })
            .then(function(data) {
                if (data) {
                    // Show an error message
                    document.getElementById('usernameError').style.display = 'block';
                } else {
                    // Hide the error message
                    document.getElementById('usernameError').style.display = 'none';
                }
            })
            .catch(function(error) {
                // Log the error for debugging
                console.error('Error:', error);

                // Show a generic error message to the user
                document.getElementById('usernameError').textContent = 'An error occurred while checking the username. Please try again later.';
                document.getElementById('usernameError').style.display = 'block';
            });
    }, 500);
});

// Check that passwords match
function validatePasswords() {
    var password = document.getElementById('password').value;
    var confirmPassword = document.getElementById('confirmPassword').value;
    var passwordError = document.getElementById('passwordError');

    if (password !== confirmPassword) {
        passwordError.textContent = 'Passwords do not match.';
        passwordError.style.color = 'red';
        passwordError.style.display = 'block';
        return false;
    } else {
        passwordError.textContent = 'Passwords match.';
        passwordError.style.color = 'green';
        passwordError.style.display = 'block';
    }

    return true;
}

// Adding input event listener for confirmPassword field
document.getElementById('confirmPassword').addEventListener('input', validatePasswords);
