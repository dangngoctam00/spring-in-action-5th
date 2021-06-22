## Taco Cloud v0.0.1

This folder contains the source code for the Taco Cloud sample from Spring in Action, 5th edition.

There may be portions of the application that are not fully functional (yet), but the code that is relevant to chapter 1 to chapter 9 should be complete and ready to run.

It's also important to note that example code tends to evolve throughout the course of a chapter. Moreover, there are often many different variants of a given piece of code presented. The sample code given here may only represent one such variant or evolution.

# Taco Cloud modules

The multi-module Maven project is made up of the following modules:

 - `tacocloud-api` : The REST API
 - `tacocloud-data` : The persistence module
 - `tacocloud-domain` : The domain types
 - `tacocloud-security` : The security module (TODO: Not fully functional yet.)
 - `tacocloud-ui` : A Typescript Angular UI
 - `tacocloud-web` : The web module (largely leftovers from previous chapters. TODO: Clean up and remove.)
 - `tacos` : The main module that pulls the other modules together and provides the Spring Boot main class.
