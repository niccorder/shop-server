# Shop's Server - A Case Study
If you have not already, please checkout the[base repo](https://github.com/niccorder/Shop)which goes over much more detailed information regarding this project.

This repository is the setup of our RESTful microservice architecture that serves up information to our [clients.](https://github.com/niccorder/shop-android/) This stack is based on the following:
- [Finagle - An open source, scalable, RPC library created by twitter](https://github.com/twitter/finagle)
    - [Finatra - an extention of finagle used for simple HTTP REST servers](https://github.com/twitter/finatra)
- [Thrift - An open source language agnostic client/server generator created by facebook, but now under the apache association](https://thrift.apache.org/)
    - You should check out the [white paper](https://thrift.apache.org/static/files/thrift-20070401.pdf)on thrift if you haven't already!

### The Architecture
I based this project off the modern micro services architecture, which is a 4 layered architecture that is currently praised as the correct way to design your microservices.
- Our **first** layer will be our nginx instance that clients interact with. Ideally it will act as our load balancer, and gateway for our microservices. This will delegate our what microservice you will be hitting, as well as what microservice instance you will be speaking with :-)
- Our **second** layer will be our REST HTTP client. This will accept your request, then will use thrift as a middle layer which will then create a client-service to speak with our datastore layer.
- Our **third** layer will be our layer in which we request data, from our actual datastore. This is good for aggregation, and security since we use a thrift middle layer. Using thrift as a middle layer, we don't have to surface this as a http endpoint which means we handle whom speaks to this layer.
- Our **fourth** and final layer is our datastore layer. Mysql, redis... etc all live here, although they have not been implemented yet :(

### Todos
This is a work in progress, and the following things are needed to be completed/built upon -
- Hooking up mysql/redis to serve up our data from our thrift services.
- A simple guide to get the project up and running on your own local machine.
- Tests ... I feel terrible not implementing any feature testing/unit testing in the initial development phase of this project. Shame on me.
- Better logging for traceability
- New micro-services relating to OAuth, user creation, as well as doing simple load testing to ensure this project will scale as necessary.
- Setting up DTab's for development and most importantly for local development.
- A metric shit-ton more things.