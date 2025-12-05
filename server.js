const http = require('http');
const fs = require('fs');
const path = require('path');

const port = 8081;

const server = http.createServer((req, res) => {
    let filePath = path.join(__dirname, req.url === '/' ? 'UserLogin.html' : req.url);
    fs.readFile(filePath, (err, data) => {
        if (err) {
            res.writeHead(200, {'Content-Type': 'text/html'});
            res.end('<h1>Welcome to Employee Attendance System</h1><p>Get started by <a href="UserLogin.html">logging in</a> or <a href="Dashboard.html">accessing dashboard</a> (after login).</p>');
        } else {
            res.writeHead(200);
            res.end(data);
        }
    });
});

server.listen(port, () => {
    console.log(`Frontend server running at http://localhost:${port}`);
    console.log('Open a browser and navigate to http://localhost:8081/Dashboard.html after logging in.');
});
