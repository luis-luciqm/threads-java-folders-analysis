<b><h1 style="text-align: center;">Arquitetura Piper And Filter</h1></b>
<b><h2>Tecnologias utilizadas</h2></b>
<ul>
    <li>Java</li>
</ul>
<b><br><h2>Informações Gerais</h2></b>
<h4>Monitoring é uma aplicação que monitora pastas do projeto e realiza operações com arquivos e pastas que tem como 3 partes:</h4>
    <p style="margin-left: 20px;"> - As Thread 1 irá Observar constantemente a Pasta 1;</p>
    <p style="margin-left: 20px;"> - Caso exista um arquivo dentro ele irá mover este arquivo para a Pasta 2;</p>
    <p style="margin-left: 20px;"> - A Thread 2 atua como observador de conteúdo da Pasta 2, caso exista um arquivo, se não houver nenhum erro de leitura (Ex: arquivo com dados do tipo String), a Thread fará as seguintes operações :</p>
        <p style="margin-left: 40px;"> - Ler o arquivo da Pasta 2;</p>
        <p style="margin-left: 40px;"> - Realizar um somatório dos dados lidos no presente aquivo lido;</p>
        <p style="margin-left: 40px;"> - Salvar os dados (nome do arquivo lido e valor do somatório) no arquivo da Pasta 3, e deletar o arquivo lido da Pasta 2;</p>
<b><br><h2 style="text-align: center;">Exemplo de execução:</h2></b>
<img style="margin-left: 23%;" src="Monitoring.gif" alt="Gif de execução">

