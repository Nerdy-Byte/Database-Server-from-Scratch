# Volcano Optimizer using Apache Calcite

## Overview

This project implements a custom query optimizer using Apache Calcite, based on the Volcano optimization framework. The optimizer transforms SQL queries into efficient execution plans by applying a series of logical and physical transformations, leveraging the extensibility of Calcite.

## Features

- **Custom Rule Sets**: Implements specific optimization rules tailored for the target data source.
- **Cost-Based Optimization**: Utilizes a cost model to evaluate and select the most efficient query execution plans.
- **Support for Various Data Sources**: The optimizer can handle multiple types of data sources, including in-memory data, databases, and files.
- **Extensible Architecture**: New rules and optimizations can be easily added.

## Setup

### Prerequisites

- **Java 11+**
- **Maven** (for managing dependencies and building the project)
- **Apache Calcite** version 1.34.0 or higher
