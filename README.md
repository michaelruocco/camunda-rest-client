# Camunda Rest Client

[![Build](https://github.com/michaelruocco/camunda-rest-client/workflows/pipeline/badge.svg)](https://github.com/michaelruocco/camunda-rest-client/actions)
[![codecov](https://codecov.io/gh/michaelruocco/camunda-rest-client/branch/master/graph/badge.svg?token=FWDNP534O7)](https://codecov.io/gh/michaelruocco/camunda-rest-client)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/272889cf707b4dcb90bf451392530794)](https://www.codacy.com/gh/michaelruocco/camunda-rest-client/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=michaelruocco/camunda-rest-client&amp;utm_campaign=Badge_Grade)
[![Quality Gate Status](https://sonarcloud.io/api/project_badges/measure?project=michaelruocco_camunda-rest-client&metric=alert_status)](https://sonarcloud.io/dashboard?id=michaelruocco_camunda-rest-client)
[![Technical Debt](https://sonarcloud.io/api/project_badges/measure?project=michaelruocco_camunda-rest-client&metric=sqale_index)](https://sonarcloud.io/dashboard?id=michaelruocco_camunda-rest-client)
[![Coverage](https://sonarcloud.io/api/project_badges/measure?project=michaelruocco_camunda-rest-client&metric=coverage)](https://sonarcloud.io/dashboard?id=michaelruocco_camunda-rest-client)
[![Lines of Code](https://sonarcloud.io/api/project_badges/measure?project=michaelruocco_camunda-rest-client&metric=ncloc)](https://sonarcloud.io/dashboard?id=michaelruocco_camunda-rest-client)
[![Maven Central](https://img.shields.io/maven-central/v/com.github.michaelruocco/camunda-rest-client.svg?label=Maven%20Central)](https://search.maven.org/search?q=g:%22com.github.michaelruocco%22%20AND%20a:%22camunda-rest-client%22)
[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT)

## Overview

This project contains a Java client library for the [Camunda REST API](https://docs.camunda.org/manual/7.5/reference/rest/)
So far it only supports a small number of the available operations but more support can be added as required

## Useful Commands

```gradle
// cleans build directories
// checks dependency versions
// checks for gradle issues
// formats code
// builds code
// runs unit tests
// runs integration tests
./gradlew clean dependencyUpdates lintGradle spotlessApply build integrationTest
```