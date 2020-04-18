var mysql = require('mysql');
var express = require('express');
var jsonParser = require('body-parser');
var crypto = require('crypto');
const { v4: uuidv4 } = require('uuid');

var con = mysql.createConnection({
    host:"localhost",
    user: "root",
    password: "harsh8797",
    database: "movieRental"
});

var app =  express();
app.use(jsonParser.json());
app.use(jsonParser.urlencoded({ extended:true }));

/* Test API */
app.get('/', (req, res, next) => {
    res.json({'data':'Working', 'error': null});
});


/* Register API */
app.post('/api/register/', (req,res) => {

    console.log(req.body);
    var data = req.body;
    var password = crypto.createHash('sha256').update(data.password).digest('hex');
    var email = data.email;
    var name = data.name;

        con.query('SELECT 1 FROM users WHERE email = ?', [email], function(err, result, fields){

            con.on('error', (err)=> {
                console.log('[MySQL Error]', err);
            });
            
            if(result && result.length){
                res.json({'data': null, 'error': 'User already exists!'});
            }else{
                var sql = "INSERT INTO users (name, email, password) values (?,?,?)";
                var values = [name, email, password];
                console.log(values);

                con.query(sql, values, function(err, result, fields){
                    con.on('error', (err)=>{
                        console.log('[MySQL Error]', err);
                    });
                    if (result.affectedRows==1){
                        res.json( {'data': 'Registered Successfully!', 'error' : null });
                    }else{
                        res.json({ 'data': null,  'error': 'Something went wrong, Please try after sometime!'});
                    }
                });
            }
        });
});

/* Login API */

app.post('/api/login/', (req,res) => {

    var data = req.body;
    var password = crypto.createHash('sha256').update(data.password).digest('hex');
    var email = data.email;

            con.query('SELECT userid FROM users WHERE email = ? AND password  = ?', [email, password], function(err, result, fields){

                con.on('error', (err)=> {
                    console.log('[MySQL Error]', err);
                    throw(err);
                });
                var userid = result[0].userid;

                if(result && result.length){

                    var userid = result[0].userid;

                    /* If Token Already Exists */
                    con.query('SELECT 1 FROM token WHERE userid = ?', [userid], function(err, result, fields){

                        con.on('error', (err)=> {
                            console.log('[MySQL Error]', err);
                        });
                        var token = uuidv4();
                        var created_date = new Date();
                        created_date.setDate(created_date.getDate());
                        var expire_date = new Date();
                        expire_date.setDate(expire_date.getDate() + 90);
                        
                        var sql = "";
                        var values;

                        /*Token Creation*/
                        if(result && result.length){
                            sql = "UPDATE token SET token = ?, created_date = ?, expire_date = ? WHERE userid = ? ";
                            values = [token, created_date, expire_date, userid];
                        }else{
                            sql = "INSERT INTO token (userid, token, created_date, expire_date) values (?,?,?,?)";
                            values = [userid, token, created_date, expire_date];
                        }

                        con.query(sql, values, function(err, result, fields){
                            con.on('error', (err)=>{
                                console.log('[MySQL Error]', err);
                            });
                            if (result.affectedRows==1){
                                res.json( {'data': {'token': token, 'userid' : userid}, 'error' : null });
                            }else{
                                res.json({ 'data': null,  'error': 'Something went wrong, Please try after sometime!'});
                            }
                        });
                    });

                }else{
                    res.json({'data': null,'error': 'Email or password is worng!'});
                }
        
            });
});


app.listen(process.env.PORT);