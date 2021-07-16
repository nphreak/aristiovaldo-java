# O Falador
# Author: Pedro 'nPhreak' Rodrigues

"O Falador" project, a Python + Arduino project. Turn audio into jaw movement.

A ideia é criar um programinha pra transformar audio em movimentos de um servo motor.

Por questões de organização, vou dividir em 3 áreas, 2 standalones e uma de integração.

Primeira parte é um App com python e interface capaz de identificar algum áudio (google ou gravação) e "separar sílabas".
Baseado nas sílabas identificadas, enviar via serial para o arduino comandos para o servo-motor conectado.
    
- O App
    - O App será dividido em 3 módulos:
        - Comunicador Serial - Mexe aí
            - Capaz de receber e enviar dados para o arduino
        - Processador de áudio - Dar nome
            - Responsável por ler as sílabas das ondas do audio.
        - Interface para facilitar o uso.

    - Barreiras/Impedimentos
        - Pouca experiência com python
        - Não faço ideia de como analisa audio.
        - Perco o foco e provavelmente vou abandonar o projeto no meio (e por meio entenda depois de uns 2 dias cutucando).

    - Vantagens
        - Já fiz interface para arduino antes, então tenho uma remota ideia de como usar comunicação serial
        - Estou me tornando uma pessoa melhor e tentado não abandonar os projetos.
        - To de férias da faculdade, então tirando as 6h do trabalho, eu tenho bastante tempo livre


- A Face
    - A face é composta de uma parte "artística" e uma eletrônica

    - Artística
        - Não faço ideia se vai dar certo.
        - Fiz uma tentativa de papel machê, se der errado, eu vou comprar uma de plástico. 
    
    - Eletrônica
        - Já tenho uma ideia do mecanismo que usarei para fazer o movimento da boca, quando começar essa parte eu escrevo mais sobre.
        - Já tenho os motores, o arduino, led e tudo o mais.

- Integração
    - Depois é integrar tudo e fazer uma montagem em que a face fique de pé e seja capaz de fingir que está falando.


Primeiro objetivo é que O Falador seja capaz de "falar" frases pela voz do Google. (Aquela do tradutor mesmo).
Dependendo da precisão do processamento de áudio do "Dar nome pro processador de audio", tentarei utilizar gravações de voz, tornando mais divertido.
