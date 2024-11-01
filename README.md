# fiap-lanchonete

Projeto para Tech Challenge da turma 9SOAT, simulando a API de uma lanchonete.

## Objetivo
Este repositŕório contém uma implementação em Quarkus da Arquitetura Hexagonal em um projeto de API para lanchonete.

### Capacidades  
No momento, a API permite:  
- Cadastro e edição de clientes
- Criação e edição de alimentos (que representam as categorias dos pedidos)
- Criação e edição de pedidos

### Instalação

#### Pré-requisitos
É necessário ter a ferramenta `Docker` e o plugin `docker-compose` instalados para utilizar o projeto.  

Para verificar se as ferramentas existem no seu sistema, abra um terminal e utilize:
```shell
docker --version
# Docker version 27.2.1, build 9e34c9b
docker compose version
# Docker Compose version v2.29.2
```
Caso algum dos comandos acima não funcione, siga os passos [nesse](https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-compose-on-ubuntu-20-04) tutorial para instalação das ferramentas.

#### Em desenvolvimento
Para inicializar o projeto em ambiente de desenvolvimento, utilize:
```shell 
docker compose -f ./docker-compose.dev.yml up -d
```

As dependências necessárias para funcionamento do projeto serão inicializadas com valores padrão de usuario e senha  
para autenticação local. 

Importante: não disponibilizar essa versão em produção, já que os segredos são facilmente descobríveis.

A API estará disponível na porta padrão `8080` após inicialização.
Para visualizar a documentação, acesse o endereço no seu navegador:
```
http://localhost:8080/q/docs
```

#### Em produção

Antes de inicializar o projeto, defina as variaveis de ambiente necessárias para inicialização no seu terminal:
```shell
# Adicione valores após o '='
# Ex.: 
# export DB_USER=fiap
export DB_USER=
export DB_PASS=
export RABBITMQ_USER=
export RABBITMQ_PASS=
```

Alternativamente, em um Linux servidor controlado, essas variaveis também podem ser adicionadas no arquivo `/etc/environment` do sistema.

Em seguida, inicie as imagens de produção utilizando o comando:
```shell
docker compose -f ./docker-compose.prod.yml up -d
```

É recomendado excluir o acesso a recursos `/q/` para evitar brechas de segurança no acesso direto a documentação Swagger.


### Contribuindo

Para contribuir com o desenvolvimento do projeto, 
recomenda-se instalar a versão `21` do `JDK`, além das dependencias padrão `Docker` e `Docker Compose`.

Em seguida, execute os containers das dependências do projeto:
```shell
# Executa o arquivo 'docker-compose.yml' que contém
# a definição do MySQL e RabbitMQ para desenvolvimento local
docker compose up -d
```

O acesso ao `MySQL` ficará disponível na porta padrão `3306`.
O acesso ao `RabbitMQ` ficara disponível na porta padrão `5672`.
Para acessar o `RabbitMQ Dashboard`, utilize a porta `15672`.

Para inicializar a API em Java Quarkus, basta executar:
```shell
./mvnw quarkus:dev
```

A API estará disponível para acesso na porta padrão `8080`, acesse esse endereço para visualizar a documentação:
```
http://localhost:8080/q/docs
```
