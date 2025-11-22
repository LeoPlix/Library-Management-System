Sistema de Gestão de Biblioteca — Especificação do Projeto

O objectivo do projeto é desenvolver um sistema completo para gestão do acervo de uma biblioteca. O sistema deve permitir operações como pesquisar obras, registar utentes, registar obras e registar requisições. O software deve ser extensível, seguindo o princípio aberto-fechado, facilitando a introdução de novas categorias, tipos de obras, regras ou comportamentos.

Conceitos e Relações do Modelo

O modelo envolve quatro conceitos principais:

Criadores

Obras

Utentes

Requisições

Podem existir mais entidades, dependendo da implementação escolhida para o projeto.

Criadores

Um criador representa um autor ou realizador.
Cada criador:

Tem um nome, usado como identificador único na aplicação

Mantém uma lista atualizada das obras que criou

Deve ser removido do sistema caso deixe de ter obras

A implementação deve permitir adicionar propriedades aos criadores sem impactar o código existente.

Obras

O sistema mantém um conjunto de obras, cada uma identificada por:

Número de obra (atribuição automática e incremental)

Título

Preço (inteiro)

Categoria

Número de exemplares disponíveis

Categorias iniciais:

Obras de referência

Obras de ficção

Obras técnicas e científicas

A aplicação deve permitir a criação de novas categorias facilmente.

Tipos de obras inicialmente suportadas:
Livros

Autores (um ou mais criadores)

ISBN (10 ou 13 caracteres)

DVDs

Realizador (um criador)

Número de registo IGAC

A arquitectura deve permitir adicionar novos tipos de obras (ex.: CDs, VHS) com impacto mínimo.

Utentes

Cada utente possui:

Número de utente (automático e incremental)

Nome

Endereço de e-mail

Situação (activo ou suspenso)

Um utente fica suspenso se entregar obras fora do prazo e permanece suspenso até devolver as obras em atraso e pagar todas as multas.

Classificação dos Utentes

Comportamentos recentes condicionam a classificação:

Faltoso: falha nas últimas 3 requisições

Cumpridor: cumpre os prazos nas últimas 5 requisições

Normal: qualquer outro caso

Faltoso → pode voltar a normal após 3 entregas consecutivas dentro do prazo

A classificação deve ser extensível para novos critérios.

Requisições

As regras para requisitar obras são verificadas por ordem (numeradas como regras do sistema):

Não pode requisitar a mesma obra duas vezes em simultâneo

Não pode estar suspenso

Não pode requisitar se não houver exemplares disponíveis

Limite de obras em simultâneo:

Normal: 3

Cumpridor: 5

Faltoso: 1

Não pode requisitar obras de referência

Não pode requisitar obras com preço superior a 25€ (excepto cumpridores)

Caso a regra 3 seja violada, o utente pode pedir notificação quando voltar a existir um exemplar disponível.

Prazos de entrega (em dias)
Exemplares	Base	Cumpridor	Faltoso
1 exemplar	3	8	2
≤ 5 exemplares	8	15	2
> 5 exemplares	15	30	2

Multas: 5€/dia de atraso (fracções contam como dia).

Prazos e regras devem ser facilmente extensíveis.

Pesquisas

O sistema deve permitir pesquisas por termo, abrangendo os campos relevantes de cada obra.
A implementação deve permitir introduzir novos métodos de pesquisa com impacto mínimo.

Alterações de Inventário

O número de exemplares de uma obra pode ser ajustado por soma ou subtracção.

Se o número de exemplares chegar a 0 → obra é removida do sistema

A remoção de obras afecta a lista de criadores

Gestão de Tempo

A data do sistema é medida em dias, começando em 1

Avançar a data implica actualizar situações de utentes e prazos

Faz parte do estado persistente

Notificações

O sistema envia notificações quando:

Uma obra é requisitada

Uma obra indisponível volta a estar disponível

Notas importantes:

Cada notificação é mostrada apenas uma vez ao utente

O interesse por disponibilidade é removido quando o utente consegue requisitar a obra

O interesse por requisições é persistente enquanto válido

Requisitos de Desenho

A arquitectura deve obedecer ao princípio aberto-fechado.

A aplicação deve permitir, com impacto mínimo:

Novos tipos de obra

Novas entidades notificáveis

Novas regras de requisição

Novos prazos

Novas classificações de utentes

Remover qualquer entidade

Funcionalidade da Aplicação

O sistema deve:

Manter dados de utentes, obras e requisições

Preservar o estado através de serialização

Permitir remoção de qualquer entidade

Carregar estado inicial a partir de ficheiro de texto (caso especificado pela propriedade Java import)

Interacção com o Utilizador

A interface é pré-implementada nas packages fornecidas (bci-app).
Os comandos recebem dados via form e escrevem via display.

As excepções de domínio não podem usar mensagens da interface

A interface usa excepções como NoSuchUserException, NoSuchWorkException, etc.

Todas as operações que falham não devem alterar o estado da aplicação (salvo indicação contrária)

Menu Principal

Inclui operações:

Abrir ficheiro de estado

Guardar estado

Mostrar data

Avançar data

Menu de Utentes

Menu de Obras

Menu de Requisições

Estas opções já estão implementadas na package bci.app.main.

Salvaguarda

Abrir: substitui o estado atual

Guardar: grava no ficheiro associado

Pergunta ao utilizador antes de descartar alterações não guardadas

Menu de Gestão de Utentes

Inclui:

Registar utente

Mostrar utente

Mostrar todos os utentes

Mostrar notificações

Pagar multa

Acesso e interacção com utilizador seguem classes da package bci.app.users.

Menu de Gestão de Obras

Inclui:

Mostrar obra

Mostrar todas as obras

Mostrar obras de criador

Alterar inventário

Pesquisa

Formatos de apresentação variam conforme o tipo (livro ou DVD).

Menu de Gestão de Requisições

Inclui:

Requisitar obra

Devolver obra

Regras e excepções seguem as definições do domínio.

Leitura Inicial de Dados em Ficheiro Texto

Formato das obras:

DVD:título:realizador:preço:categoria:númeroIGAC:exemplares
BOOK:título:autor1,autor2,...:preço:categoria:ISBN:exemplares


Formato dos utentes:

USER:nome:email
