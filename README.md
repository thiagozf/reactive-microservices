# Reactive Microservices

Este projeto demonstra como os conceitos de Event Sourcing e CQRS podem ser aplicados em uma arquitetura de microsserviços. O exemplo consiste em um e-commerce e mostra a interação entre dois serviços: compras e produtos. Ele foi utilizado como referência para a minha talk no 9° Meetup do [Criciúma DEV](https://criciumadev.com.br/)! 

# Como executar?

Você vai precisar da ☕ [JDK 8+](https://www.oracle.com/technetwork/pt/java/javase/downloads) para compilar o projeto e do 🐋[docker-compose](https://docs.docker.com/compose/) para executar tudo sem ter dores de cabeça :)

```
$ cd products
$ ./mvnw clean package
$ cd ../orders
$ ./mvnw clean package
$ cd ..
$ docker-compose up -d orders
```

Depois, você pode usar as coleções do Postman deste repositório para interagir com os serviços.