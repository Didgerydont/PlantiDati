document.addEventListener('DOMContentLoaded', (event) => {
    document.getElementById("loginForm").addEventListener("submit", function(event){
        event.preventDefault()

        fetch('/auth/authenticate', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify({
                username: document.getElementById('username').value,
                password: document.getElementById('password').value
            })
        })
        .then(response => response.json())
        .then(data => console.log(data))
        .catch((error) => {
            console.error('Error:', error);
        });
    });
});
