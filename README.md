# Portfolio-Management
A Leading financial Social Organization develops a customer portal, which consumes  Microservices to view their portfolio information and sell their assets.

MicroServices are:

1.Daily Share Price Module: Middleware Microservices that performs getting daily      share price of a stock
 Port No:http://localhost:7000
 
2.Daily Mutual Fund NAV:This mdule is a Middleware Miroservice that performs getting NAV value of a Mutual Fund
 Port No:http://localhost:7001
 
3.Calculate Net worth Module: This Microservice performs the following operations:
a.Calculate the current value of share holdings and mutual fund holdings and find out the current value or net-worth
b.Sell Assets and determine the final net-worth
 Port No:http://localhost:7002
 
4.Authorization Microservice:This Microservice take care of Authentication and Authorization
-After every successful Login JWT token gets generated
-Every Request made by user authorized here.
Port No:http://localhost:7003

5.Customer Portal: An web portal that allows a customer to Login and allows to do folloing operations
-Login/Logout
-View the portfolio holdings and networth
-Sell assets
 It's a front-end part built using Angular 
 Port No:http://localhost:4200
 To run:ng serve
 To bulid:ng build
 
 In addition to above Microservices, there are 2 more microservices added in the project:
 
 6.Api-Gateway : It act as an Entry-point for all the requesting/accessing the backend microservices from UI(Front-end) part.It has a single URL to access backend microservices makes the development process easy.
 Port No:http://localhost:7500/stock/**
 Port No:http://localhost:7500/mutualfund/**
 Port No:http://localhost:7500/calculate/**
 
 7.Service Registry:It is an Eureka Server. all other microservices registered as client in eureka server.
Port No:http://localhost:8761
