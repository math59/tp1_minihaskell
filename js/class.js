//Mapa
function Mapa(largura, altura){
	this.canvas = document.createElement("canvas");
	this.canvas.width = this.width = largura; //largura da tela
	this.canvas.height = this.height = altura; //altura da tela
	this.modelo2D = this.canvas.getContext("2d"); //objeto com métodos e propriedades pra trabalhar no canvas em 2d
	//mudar endereço fonte do background conforme pc
	this.canvas.style.backgroundImage = "url('file:///C:/Users/Matheus/Desktop/UnB/20181/LP/projeto_spinv/src/space3.gif')";

	document.body.appendChild(this.canvas);
};

Mapa.prototype.refresh = function(){ //atualiza o canvas para não gerar copia de sprites quando se movem
	this.modelo2D.clearRect(0, 0, this.width, this.height);
};

Mapa.prototype.mostraSprite = function(spt, x, y){ //objeto de Map para gerar o sprite no canvas
	this.modelo2D.drawImage(spt.img, spt.x, spt.y, spt.largura, spt.altura, x, y, spt.largura, spt.altura);
};

Mapa.prototype.mostraProjetil = function(projetil){ //objeto que gera projetéis da nave e do aliens
	this.modelo2D.fillStyle = projetil.color;
	this.modelo2D.fillRect(projetil.x, projetil.y, projetil.width, projetil.height);
};

/**Sprites
 * Função com as características do sprite, img = arquivo
 * x, y, largura e altura são as variáveis para fazer uma espécie de
 * recorte na imagem e isolar o sprite que queremos.
 */
function Sprite(img, x, y, largura, altura){
	this.img = img;
	this.x = x;
	this.y = y;
	this.largura = largura;
	this.altura = altura;
};

//Controle do usuario
function uControle(){
	this.aperta = {};
	this.pressiona = {};
	var ref = this; //referencia a propria função uControle();

	document.addEventListener("keydown", function(evento){ //evento quando um botão é apertado
		ref.aperta[evento.keyCode] = true;
	});
	document.addEventListener("keyup", function(evento){ //evento quando um botão é solto
		delete ref.aperta[evento.keyCode];
		delete ref.pressiona[evento.keyCode];
	});
};

uControle.prototype.botaoAperta = function(code){ //code é o código unicode da tecla ativada
	return this.aperta[code];
};

uControle.prototype.botaoPressiona = function(code){
	if (this.pressiona[code]){
		return false;
	}
	else if (this.aperta[code]){
		return this.pressiona[code] = true;
	}
	return false;
};

//Projetil
function atiraProjetil(x, y, vel, largura, altura, cor){
	this.x = x;
	this.y = y;
	this.vel = vel;
	this.width = largura;
	this.height = altura;
	this.color = cor;
};

atiraProjetil.prototype.atualiza = function(){ //realiza o movimento do projétil pelo mapa
	this.y += this.vel;
};

//Função que calcula se projetil colide com alvos
function colide(ax, ay, aLargura, aAltura, bx, by, bLargura, bAltura){
	return ax < bx+bLargura && bx < ax+aLargura && ay < by+bAltura && by < ay+aAltura;
};