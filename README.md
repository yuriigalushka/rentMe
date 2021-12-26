### registration

POST http://localhost:8080/auth/registration
Content-Type:application/json

{
	"username":"anna@mail.com",
	"password":"Pass123456~",
	"firstName":"string",
	"lastName":"string",
	"phone":"string",
	"role":"string"
}

### login

POST http://localhost:8080/auth/login
Content-Type:application/json

{
	"username":"admin@mail.com",
	"password":"Pass123456~"
}

