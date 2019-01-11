# Reactive Microservices

Este projeto demonstra como os conceitos de Event Sourcing e CQRS podem ser aplicados em uma arquitetura de microsserviços. O exemplo consiste em um *e-commerce* e mostra a interação entre dois serviços: **orders** e **products**. Ele foi utilizado como referência para a minha talk no 9° Meetup do [Criciúma DEV](https://criciumadev.com.br/)! 

## Products

[![Build Status](https://travis-ci.org/thiagozf/reactive-microservices.svg?branch=master)](https://travis-ci.org/thiagozf/reactive-microservices)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=thiagozf_reactive-microservices-products&metric=alert_status)](https://sonarcloud.io/dashboard/index/thiagozf_reactive-microservices-products) [![SonarCloud Coverage](https://sonarcloud.io/api/project_badges/measure?project=thiagozf_reactive-microservices-products&metric=coverage)](https://sonarcloud.io/component_measures/metric/coverage/list?id=thiagozf_reactive-microservices-products)
[![SonarCloud Bugs](https://sonarcloud.io/api/project_badges/measure?project=thiagozf_reactive-microservices-products&metric=bugs)](https://sonarcloud.io/component_measures/metric/reliability_rating/list?id=thiagozf_reactive-microservices-products)
[![SonarCloud Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=thiagozf_reactive-microservices-products&metric=vulnerabilities)](https://sonarcloud.io/component_measures/metric/security_rating/list?id=thiagozf_reactive-microservices-products)

Serviço responsável por gerenciar o catálogo de produtos do *e-commerce*. Ele publica eventos sobre **produtos** para todos os serviços interessados nestes dados. Além disso, utiliza os mesmos eventos para montar o estado atual do catálogo (*event-sourcing*).

## Orders

[![Build Status](https://travis-ci.org/thiagozf/reactive-microservices.svg?branch=master)](https://travis-ci.org/thiagozf/reactive-microservices)
[![Quality Gate](https://sonarcloud.io/api/project_badges/measure?project=thiagozf_reactive-microservices-orders&metric=alert_status)](https://sonarcloud.io/dashboard/index/thiagozf_reactive-microservices-orders) [![SonarCloud Coverage](https://sonarcloud.io/api/project_badges/measure?project=thiagozf_reactive-microservices-orders&metric=coverage)](https://sonarcloud.io/component_measures/metric/coverage/list?id=thiagozf_reactive-microservices-orders)
[![SonarCloud Bugs](https://sonarcloud.io/api/project_badges/measure?project=thiagozf_reactive-microservices-orders&metric=bugs)](https://sonarcloud.io/component_measures/metric/reliability_rating/list?id=thiagozf_reactive-microservices-orders)
[![SonarCloud Vulnerabilities](https://sonarcloud.io/api/project_badges/measure?project=thiagozf_reactive-microservices-orders&metric=vulnerabilities)](https://sonarcloud.io/component_measures/metric/security_rating/list?id=thiagozf_reactive-microservices-orders)

Serviço que responde os comandos dos usuários relacionados a **compras**. Os comandos enviados pelos usuários a este serviço geram eventos, que por sua vez são utilizados para montar o estado atual de cada compra realizada.

O serviço de compras também utiliza os eventos publicados pelo serviço de produtos para verificações de regras de negócio - *ex.: verificar se o produto tem saldo em estoque*.

# Como executar?

Você vai precisar da ☕ [JDK 8+](https://www.oracle.com/technetwork/pt/java/javase/downloads) para compilar o projeto e do 🐋[docker-compose](https://docs.docker.com/compose/) para executar tudo sem ter dores de cabeça :)

```
$ cd products
$ ./mvnw clean package
$ cd ../orders
$ ./mvnw clean package
$ cd ..
$ docker-compose up -d orders products
```

Depois, você pode usar as coleções do Postman deste repositório para interagir com os serviços.