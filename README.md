# fiap-lanchonete

Projeto para Tech Challenge da turma 9SOAT, simulando a API de uma lanchonete.

## Objetivo

Este repositŕório contém uma implementação em Quarkus da Arquitetura Hexagonal em um projeto de API para lanchonete.

### Capacidades

No momento, a API permite:

- Cadastro e edição de clientes
- Criação e edição de alimentos (que representam as categorias dos pedidos)
- Criação e edição de pedidos

### Considerações
Esse trecho contém considerações da equipe quanto a implementação e experiência de desenvolvimento na Arquitetura Hexagonal.  

Após o desenvolvimento desse projeto, chegamos a conclusão de que a arquitetura hexagonal dificulta a prototipagem rápida, mas melhora 
a experiência de manutenção e criação de novas features.  

A principal vantagem dessa arquitetura é a possibilidade de fácil substituição de dependências, o que diminui o vendor lock-in que tanto enfrentamos ao projetar 
aplicações Cloud.  
Por exemplo, para trocar o sistema de mensageria de `rabbitmq` para qualquer outra solução, basta implementar as interfaces relacionadas (`EstadoPedidoEmitter`, por exemplo) 
e adicionar a lógica de comunicação necessária. A aplicação interna (`domain`) não se importa com o meio de comunicação das mensagens, apenas que elas sejam implementadas.
Achamos relevante utilizar o serviço extra `rabbitmq` justamente para comprovar essa hipótese.  

A arquitetura mantém regras bem definidas sobre como deve funcionar o fluxo interno e externo da aplicação (os chamados `ports` e `adapters`), o que pode auxiliar 
o time de desenvolvimento a manter um padrão de estrutura e qualidade de código, independente do tamanho da aplicação.

### Instalação

### Docker - Em desenvolvimento

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

#### Inicialização do projeto

Crie um arquivo .env e coloque essas variáveis de ambiente:

```shell
MERCADO_PAGO_URL=https://api.mercadopago.com/
ID_CONTA=662144664
ID_LOJA=1B2D92F23
URL_NOTIFICACAO=https://www.yourserver.com/notifications
MERCADO_PAGO_API_KEY=TEST-8402790990254628-112619-4290252fdac6fd07a3b8bb555578ff39-662144664
```

Para inicializar o projeto:

```shell
docker compose -f ./docker-compose.yml up -d
```

As dependências necessárias para funcionamento do projeto serão inicializadas com valores padrão de usuario e senha  
para autenticação local.

Importante: não disponibilizar essa versão em produção, já que os segredos são facilmente descobríveis.

A API estará disponível na porta padrão `8080` após inicialização.
Para visualizar a documentação, acesse o endereço no seu navegador:

```
http://localhost:8080/q/docs
```

### Minikube - Em Desenvolvimento

#### Pré-requisitos

É necessário ter a ferramenta `Docker`, `Kubectl` e `Minikube` instalados para utilizar o projeto.

Para verificar se as ferramentas existem no seu sistema, abra um terminal e utilize:

```shell
docker --version
# Docker version 27.2.1, build 9e34c9b
docker compose version
# Docker Compose version v2.29.2

kubectl version
Client Version: v1.32.0
Kustomize Version: v5.5.0
Server Version: v1.31.0

minikube version --short
v1.34.0
```

Caso algum dos comandos acima não funcione, siga os passos [nesse](https://www.digitalocean.com/community/tutorials/how-to-install-and-use-docker-compose-on-ubuntu-20-04) tutorial para instalação das ferramentas.

#### Inicialização do projeto

Instancie o minikube:
```shell
minikube start
```

Aplique as configurações do yaml:
```shell
kubectl apply -f .kube/fiap.yml
kubectl apply -f .kube/mysql.yml
kubectl apply -f .kube/secret.yml
```

Pegue a url do projeto fiap-lanchonete:
```shell
minikube service fiap-lanchonete --url
```

A sua `URL` será essa, conforme a imagem:

![image](https://github.com/user-attachments/assets/7963a575-8944-4a1f-ba42-8883ae1abf34)

Basta adicionar um `/q/docs` ao final dessa URL para ter acesso aos recursos disponibilizados:

![image](https://github.com/user-attachments/assets/1a3d1556-dced-4605-a261-ea69ed67437e)


##### Em produção

É recomendado excluir o acesso a recursos `/q/` para evitar brechas de segurança no acesso direto a documentação Swagger.

Também é recomendado construir uma definição do `compose` secreta, com varieis de ambientes seguras, além de servir
a aplicação principal atráves de um proxy reverso (como nginx, traefik e caddy).

### AWS

#### Pré-requisitos

É necessário ter a ferramenta `AWS CLI` e `Kubectl` instalados e uma conta na AWS para utilizar o projeto.

Para verificar se as ferramentas existem no seu sistema, abra um terminal e utilize:

```shell
docker --version
# Docker version 27.2.1, build 9e34c9b
docker compose version
# Docker Compose version v2.29.2

kubectl version
Client Version: v1.32.0
Kustomize Version: v5.5.0
Server Version: v1.31.0

minikube version --short
v1.34.0
```


### Contribuindo

Para contribuir com o desenvolvimento do projeto,
recomenda-se instalar a versão `21` do `JDK`, além das dependencias padrão `Docker` e `Docker Compose`.

Em seguida, execute os containers das dependências do projeto:

```shell
# Executa o arquivo 'docker-compose.dev.yml' que contém
# a definição do MySQL e RabbitMQ para desenvolvimento local
docker compose -f docker-compose.dev.yml up -d
```

O acesso ao `MySQL` ficará disponível na porta padrão `3306`.
O acesso ao `RabbitMQ` ficara disponível na porta padrão `5672`.
Para acessar o `RabbitMQ Dashboard`, utilize a porta `15672`.

Para inicializar a API em Java Quarkus, basta executar:

```shell
./mvnw clean quarkus:dev
```

A API estará disponível para acesso na porta padrão `8080`, acesse esse endereço para visualizar a documentação:

```
http://localhost:8080/q/docs
```

## Fase 2
### Desenho de arquitetura da aplicação
![](/public/img/desenho_aplicacao_fase_2.png)  
Clique [aqui](https://excalidraw.com/#json=1p_ph-W69GxI4F9MZgRvu,eWeftMENraZ0MDIHP8lLTg) para visualizar em tela cheia.

### Documentação dos endpoints
É recomendável utilizar a documentação Swagger acessível após subir a aplicação (`/q/docs`), ou você pode baixar 
[esse](/public/static/openapi.json) arquivo JSON contendo a especificação OpenAPI 3.0 da API. Basta abrir o arquivo em algum 
editor com suporte a leitura do formato.

### Video de apresentação do projeto
Fizemos um vídeo detalhando a arquitetura do projeto, acessível [aqui](https://youtu.be/3f4BSngvKEE).  
Nele também tem um guia da ordem de chamada dos endpoints.

### Guia completo
O fluxo das chamadas segue basicamente a ordem de:
1. Cadastrar alimentos
2. Cadastrar cliente (opcional, pode ser nulo)
3. Criar Pedido
4. Webhook de confirmação de pagamento
5. Alterar estado do pedido (checkout) 
6. Alterar estado do pedido (em preparação)
7. Alterar estado do pedido (pronto)

