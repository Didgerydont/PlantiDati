// Call JWT from sessionStorage for authentication
let jwt = sessionStorage.getItem('jwt');

fetch('/api/protected', {
    method: 'GET',
    headers: {
        'Authorization': 'Bearer ' + jwt,
        'Content-Type': 'application/json'
    },
})
.then(response => response.json())
.then(data => console.log(data))
.catch((error) => {
    console.error('Error:', error);
});