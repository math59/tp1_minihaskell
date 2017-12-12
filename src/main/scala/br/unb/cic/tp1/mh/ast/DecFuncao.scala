package br.unb.cic.tp1.mh.ast


class DecFuncao(val nome: String, val argFormal : Array[String], val corpo : Expressao)
//Alterei argFormal para um Array para suporte a múltiplos parâmetros