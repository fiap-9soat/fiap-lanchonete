# fiap-lanchonete

Projeto para Tech Challenge da turma 9SOAT, simulando a API de uma lanchonete.

## Objetivo

Este repositório contém uma implementação em Quarkus de uma API de lanchonete.

### Capacidades

No momento, a API permite:

- Cadastro e edição de clientes
- Criação e edição de produtos (que representam as categorias dos pedidos)
- Criação e edição de pedidos

## Instalação

### Local

Essa configuração é somente para rodar localmente em desenvolvimento.

#### Inicialização do projeto

Crie um arquivo .env e coloque essas variáveis de ambiente:

```shell
DB_URL=localhost:3306
MYSQL_USER=fiap
MYSQL_PASSWORD=fiap
MERCADO_PAGO_URL=https://api.mercadopago.com/
MERCADO_PAGO_ID_CONTA=662144664
MERCADO_PAGO_ID_LOJA=1B2D92F23
MERCADO_PAGO_URL_NOTIFICACAO=https://www.yourserver.com/notifications
MERCADO_PAGO_API_KEY=TEST-8402790990254628-112619-4290252fdac6fd07a3b8bb555578ff39-662144664
```

Rode o comando
`./mvnw quarkus:dev`

A API estará disponível na porta padrão 8080 após inicialização. Para visualizar a documentação, acesse o endereço no seu navegador:

http://localhost:8080/q/docs


### Docker

Essa configuração é recomendada somente para ambientes de desenvolvimento.

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
DB_URL=localhost:3306
MYSQL_USER=fiap
MYSQL_PASSWORD=fiap
MERCADO_PAGO_URL=https://api.mercadopago.com/
MERCADO_PAGO_ID_CONTA=662144664
MERCADO_PAGO_ID_LOJA=1B2D92F23
MERCADO_PAGO_URL_NOTIFICACAO=https://www.yourserver.com/notifications
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

### Kubernetes

Essa é a configuração recomendada para simular o fluxo do ambiente produtivo.  
Para deploy em ambiente de produção, recomendamos seguir o passo a passo especificado no repositório de `infra`:
https://github.com/fiap-9soat/fiap-lanchonete-infra

#### Pré-requisitos

É necessário ter a ferramenta `Docker`, `kubectl` instalados para utilizar o projeto.  
Também é necessário providenciar um cluster Kubernetes funcional para realizar a configuração com `kubectl`.  
Para ambientes de desenvolvimento/local, o [Minikube](https://minikube.sigs.k8s.io/docs/start/).  
Para ambientes produtivos, uma ferramenta gerenciada, como o [AWS EKS](https://aws.amazon.com/pt/eks/) é recomendada.

Para verificar se as ferramentas existem no seu sistema, abra um terminal e utilize:

```shell
docker --version
# Docker version 27.2.1, build 9e34c9b
docker compose version
# Docker Compose version v2.29.2

kubectl version
# Client Version: v1.32.0
# Kustomize Version: v5.5.0
# Server Version: v1.31.0

# CASO esteja utilizando minikube
minikube version --short
# v1.34.0
```

#### Inicialização do projeto

Essa parte do guia assume que o `kubectl` já está disponível e apontando para um cluster Kubernetes funcional.
Para testes locais, pode-se utilizar o guia do [Minikube](#minikube)

Na pasta raiz do projeto, aplique as configurações do yaml:

```shell
kubectl apply -f .kube/fiap.yml
kubectl apply -f .kube/mysql.yml
kubectl apply -f .kube/secret.yml
```

Após alguns minutos, o Kubernetes finalizará o pull e deploy das imagens `mysql` e `lamarcke/fiap-lanchonete`, correspondentes aos serviços
utilizados pela aplicação.

##### Minikube

Caso esteja utilizando o `Minikube`, verifique a URL do projeto fiap-lanchonete:

```shell
minikube service fiap-lanchonete --url
```

A sua `URL` será essa, conforme a imagem:

![image](https://github.com/user-attachments/assets/7963a575-8944-4a1f-ba42-8883ae1abf34)

Basta adicionar `/q/docs` ao final dessa URL para ter acesso aos recursos disponibilizados:

![image](https://github.com/user-attachments/assets/1a3d1556-dced-4605-a261-ea69ed67437e)

#### HPA

A configuração do [HPA](https://kubernetes.io/docs/tasks/run-application/horizontal-pod-autoscale-walkthrough/) pode variar um
pouco dependendo do ambiente.  
É necessário que o cluster possua alguma instância do [metrics-server](https://github.com/kubernetes-sigs/metrics-server#readme)
funcionando.

Para o `Minikube`, execute esse comando para habilitar o `metrics-server`:

```shell
minikube addons enable metrics-server
```

Com o `metrics-server` habilitado, basta executar esse comando para fazer o escalamento automatico da API principal:

```shell
kubectl autoscale deployment fiap-lanchonete --cpu-percent=50 --min=2 --max=10
```

Essa configuração instrui o Kubernetes a manter pelo menos 2 replicas da aplicação, com um máximo de 10 conforme demanda
de uso de CPU. Existem outras maneiras de realizar esse escalonamento, como por uso de memória ou requisições.

#### Escalonamento de Dependencias

Para dependencias externas que não são 'stateless', como o banco de dados `MySQL`, é recomendado
utilizar as configurações de clusterização da propria dependencia, já que simplesmente adicionar
mais replicas/pods não é o suficiente para garantir a tolerância a falhas.

Em ambientes produtivos, é recomendado utilizar um serviço gerenciado, como o
[Amazon RDS para MySQL](https://aws.amazon.com/pt/rds/mysql/).

### Produção

#### Terraform
Para os ambientes produtivos, essa organização possui uma configuração automatizada através do Terraform com a integração do 
AWS. Basta seguir o guia no repositório de [infra](https://github.com/fiap-9soat/fiap-lanchonete-infra).  
Os passos abaixo só são necessários caso não seja viavél a utilização do Terraform.  

#### Manual

Além da necessidade da ferramenta `kubectl`, também é necessário realizar a instalação e configuração
da `AWS CLI` e do `AWS EKS CLI`.

É necessário ter a ferramenta `AWS CLI` e `Kubectl` instalados e uma conta na AWS para utilizar o projeto.

Siga [este](https://docs.aws.amazon.com/eks/latest/userguide/setting-up.html) guia oficial para realizar a instalação dos requisitos.

Alternativamente, também é possível criar uma instância do `AWS EC2` associada ao cluster para realizar a configuração diretamente
nos servidores da AWS. A instância vem pré-configurada com as ferramentas necessárias e
(em alguns casos) com a conexão ao cluster, além de ser possível realizar comandos diretamente do navegador com o
`AWS CloudShell` ou via conexão `SSH`.
Essa é uma alternativa segura para configuração e testes, já que as credenciais serão gerenciadas automaticamente
pela `AWS`.

Após a configuração das ferramentas, deve-se disponibilizar o Cluster e NodeGroup a serem utilizados pela aplicação.
Siga [este](https://docs.aws.amazon.com/pt_br/eks/latest/userguide/create-managed-node-group.html) guia para realizar a configuração do NodeGroup.
Esse passo é importante, já que o NodeGroup é responsável por delegar 'onde' a aplicação realmente vai ser executada.

Verifique se ambos o Cluster e NodeGroup estejam com status de `Created` e sem nenhum problema de healthcheck antes de prosseguir.

#### Configuração

Para realizar o deploy da aplicação, basta seguir o [guia](#inicialização-do-projeto-1) padrão de inicialização, presente neste repositório.  
O `AWS EKS` possui algumas ferramentas proprietarias que podem ser configuradas separadamente,
como o [AWS LoadBalancer Controller](https://docs.aws.amazon.com/pt_br/eks/latest/userguide/aws-load-balancer-controller.html),
mas estes não são obrigatórios para a configuração da aplicação.

#### AWS EC2

Uma instância do `AWS EC2` pode ser utilizada tanto para configurar o cluster gerenciado do `AWS EKS`, quanto para executar
uma instância do `Minikube` para testes. A segunda opção não é recomendada para ambientes produtivos.
[Este](https://crishantha.medium.com/running-minikube-on-aws-ec2-e845337a956) guia pode ser utilizado como base para configuração.

### Contribuindo

Para contribuir com o desenvolvimento do projeto,
recomenda-se instalar a versão `21` do `JDK`, além das dependencias padrão `Docker` e `Docker Compose`.

Também é necessário configurar o arquivo `.env` localmente, seguindo o [exemplo](#docker)
da seção de instalação deste guia.

Em seguida, execute os containers das dependências do projeto:

```shell
# Executa o arquivo 'docker-compose.dev.yml' que contém
# a definição do MySQL para desenvolvimento local
docker compose -f docker-compose.dev.yml up -d
```

O acesso ao `MySQL` ficará disponível na porta padrão `3306`.

Para inicializar a API em Java Quarkus, basta executar:

```shell
./mvnw clean quarkus:dev
```

A API estará disponível para acesso na porta padrão `8080`, acesse esse endereço para visualizar a documentação:

```
http://localhost:8080/q/docs
```

## Utilização

Disponibilizamos na raiz do repositório os arquivos de coleção pré-configurados, com documentação e um passo a passo explicito 
para realizar o fluxo comum de utilização da API:
1. CRUD para produtos
2. CRUD para clientes
3. CRUD para pedidos
4. Fluxos relacionados ao pedido (recebimento, confirmação de pagamento, cancelamento)

### Coleções
Recomendamos que seja utilizado o `Postman`, `Bruno`, ou algum programa de testagem de APIs compatível.  

Para o `Postman`, utilize o arquivo `fiap-lanchonete.postman.json`  
Para o `Bruno`, utilize o arquivo `fiap-lanchonete.bruno.json`  

Após a importação, configure a variavel `API_URL` para o valor correto, normalmente `http://localhost:8080` em ambiente local.

![](/public/img/print_passo_a_passo_requisicoes.png)

### Autenticação

Durante o fluxo de utilização da API, caso o usuário esteja autenticado, o endpoint de criação de pedidos utiliza o código 
de cliente relacionado ao usuario autenticado atualmente, não sendo necessário criar um cliente para esses casos.  

**Importante**: Para ambiente produtivo (ex: subida da API pelo fluxo do Terraform da organização), é **obrigatório** 
repassar o `IdToken` recebido na autenticação, através do header `Authorization`.  
Para mais informações, leia a documentação do [projeto de autenticação](https://github.com/fiap-9soat/fiap-lanchonete-auth).

_Considera-se um usuario autenticado quando um token `JWT` válido é repassado no header `Authorization` e possui as informações necessárias 
oferecidas pelo provedor de autenticação (ex.: Cognito)_