var http = require('http');
var mysql = require('mysql');
// http.createServer(function(req, res){

//     res.write("Hello World.");
//     res.end();
// }).listen(8080);

var con = mysql.createConnection({
    host:"localhost",
    user: "root",
    password: "harsh8797"
});

var fs = require('fs');
fs.writeFile('demo.txt', "Demo Content", function(error){
    if(error) throw error;
    console.log("Content Added.");
});

fs.unlink('demo.txt', function(error){
    if(error) throw error;
    console.log("File Deleted.");
});