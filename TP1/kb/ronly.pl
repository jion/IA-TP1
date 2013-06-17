% Autor:
% Fecha: 06/06/2013
% The code below is neccessary for the framework to modify these sentences.

:- dynamic situacionActual/1,nivelActual/1,percepcion/3,executedAction/2,
en/3,orientacion/2,sostiene/1,pasoPor/4,candadoAbierto/1,miga/3,vioCandado/1,estado/2,ya/2.


% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%                              Diagnostic rules                                    %
% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% percepcion([arriba,abajo,izquierda,derecha],[llave,candado,salida],N)

esSalida(X, Y, N)   :- percepcion(_,[_,_,salida],N)        ,situacionActual(S), nivelActual(N),en(X,Y,S).
hayCandado(X, Y, N) :- percepcion(_,[_,candado,_],N)       ,situacionActual(S), nivelActual(N),en(X,Y,S).
hayLlave(X, Y, N)   :- percepcion(_,[llave,_,_],N)         ,situacionActual(S), nivelActual(N),en(X,Y,S).
paredNorte(X, Y, N) :- percepcion([paredArriba,_,_,_],_,N) ,situacionActual(S), nivelActual(N),en(X,Y,S).
paredSur(X, Y, N)   :- percepcion([_,paredAbajo,_,_],_,N)  ,situacionActual(S), nivelActual(N),en(X,Y,S).
paredEste(X, Y, N)  :- percepcion([_,_,_,paredDer],_,N)    ,situacionActual(S), nivelActual(N),en(X,Y,S).
paredOeste(X, Y, N) :- percepcion([_,_,paredIzq,_],_,N)    ,situacionActual(S), nivelActual(N),en(X,Y,S).

% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%                             Successor state axioms                              %
% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% Accion Avanzar %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

 % Aca realmente cambio la posición
est(S1) :- S1 > 0,
           S is S1-1,
		   not(ya(1,S)),
		   executedAction(avanzar,S),
		   nivelActual(N),
		   en(X,Y,S),
		   pasoPor(X,Y,C,N),
		   not(paredAdelante(X,Y,N)),
		   posicionAdelante(X1,Y1,S),
		   C1 is C+1,
		   retract(pasoPor(X,Y,C,N)),
		   asserta(pasoPor(X,Y,C1,N)),
		   asserta(en(X1,Y1,S1)),
		   asserta(ya(1,S)),
		   asserta(estado(vieja,S)).

est(S1) :- S1 > 0,
           S is S1-1,
		   not(ya(1,S)),
		   executedAction(avanzar,S),
		   nivelActual(N),
		   en(X,Y,S),
		   not(pasoPor(X,Y,_,N)),
		   not(paredAdelante(X,Y,N)),
		   posicionAdelante(X1,Y1,S),
		   asserta(pasoPor(X,Y,1,N)),
		   asserta(en(X1,Y1,S1)),
		   asserta(ya(1,S)),
		   asserta(estado(nueva,S)).

% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Acciones relacionadas con las migas %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% %%%%%%%%%%%%%%%%%%%%%%
% Estado JC (Estandar)
% %%%%%%%%%%%%%%%%%%%%%%

% Si ve un candado (transicion)
est(S1) :- S1 > 0,
           S is S1-1,
		   ya(1,S),
		   executedAction(avanzar,S),
		   nivelActual(N),
		   not(sostiene(N)),         % Estado JC
		   not(vioCandado(N)),       %
		   en(X,Y,S),
           hayCandado(X,Y,N),
		   asserta(vioCandado(N)),
		   asserta(estado(2,S)).
		   
% %%%%%%%%%%%%%%%%%%%%%%
% Estado ComeMiga
% %%%%%%%%%%%%%%%%%%%%%%
est(S1) :- S1 > 0,
           S is S1-1,
		   ya(1,S),
		   executedAction(avanzar,S),
			not(ya(x,S)),		   
		   nivelActual(N),
		   sostiene(N),            % Condicion Come-Miga
		   en(X,Y,S),
		   miga(X,Y,N),                 % con miga %
		   retract(miga(X,Y,N)),
		   asserta(estado(3,S)),
		   asserta(ya(x,S)).

est(S1) :- S1 > 0,
           S is S1-1,
		   ya(1,S),
		   executedAction(avanzar,S),
		   not(ya(x,S)),
		   nivelActual(N),
		   sostiene(N),            % Condicion Come-Miga
		   en(X,Y,S),
		   not(miga(X,Y,N)),            % sin miga %
		   asserta(estado(4,S)),
		   asserta(ya(x,S)).
		   
% %%%%%%%%%%%%%%%%%%%%%%
% Estado DejaMiga
% %%%%%%%%%%%%%%%%%%%%%%
est(S1) :- S1 > 0,
           S is S1-1,
		   ya(1,S),
		   executedAction(avanzar,S),
		   not(ya(x,S)),
		   nivelActual(N),
		   vioCandado(N),           %  Condicion deja-miga
		   not(sostiene(N)),        %
		   en(X,Y,S),
		   miga(X,Y,N),		   % con miga %
		   asserta(estado(6,S)),
		   asserta(ya(x,S)).
		   
est(S1) :- S1 > 0,
           S is S1-1,
		   ya(1,S),
		   executedAction(avanzar,S),
		   not(ya(x,S)),
		   nivelActual(N),
		   vioCandado(N),            %  Condicion deja-miga
		   not(sostiene(N)),         %
		   en(X,Y,S),
		   not(miga(X,Y,N)),                             % sin miga %                
		   asserta(miga(X,Y,N)),
		   asserta(estado(5,S)),
		   asserta(ya(x,S)).


		   
% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
		   


% Accion Girar Derecha
est(S1) :- S1 > 0,
           S is S1-1,
		   not(ya(1,S)),
           executedAction(girarDer,S),
           orientacion(O,S),
           T is mod(O + 1, 4),
           asserta(orientacion(T,S1)),
		   asserta(estado(8,S)),
		   asserta(ya(1,S)).

% Accion Girar Izquierda
est(S1) :- S1 > 0,
           S is S1-1,
		   not(ya(1,S)),
           executedAction(girarIzq,S),
           orientacion(O,S),
           T is mod(O + 3, 4),
           asserta(orientacion(T,S1)),
		   asserta(estado(9,S)),
		   asserta(ya(1,S)).
          
% Accion tomar llave
est(S1) :- S1 > 0,
           S is S1-1,
		   not(ya(1,S)),
           executedAction(tomarLlave,S),
           nivelActual(N),
		   en(X,Y,S),
           hayLlave(X,Y,N),
           not(sostiene(N)),
           asserta(sostiene(N)),
		   asserta(estado(11,S)),
		   asserta(ya(1,S)).

          
% Accion Abrir Candado
est(S1) :- S1 > 0,
           S is S1-1,
		   not(ya(1,S)),
           executedAction(abrirCandado,S),
           nivelActual(N),
		   en(X,Y,S),
           hayCandado(X,Y,N),
           sostiene(N),
		   asserta(candadoAbierto(N)),
           % retract(hayCandado(X,Y,N)),
		   asserta(estado(12,S)),
		   asserta(ya(1,S)).
           
% Accion Salir
est(S1) :- S1 > 0,
           S is S1-1,
		   not(ya(1,S)),
           executedAction(salir,S),
           % nivelActual(N),
           en(_,Y,S),
		   asserta(en(0,Y,S1)),
		   asserta(estado(13,S)),
		   asserta(ya(1,S)),
		   asserta(ya(2,S)).

% Accion distinta de Avanzar
est(S1):- S1 > 0,
          S is S1-1,
		  not(ya(2,S)),
          executedAction(P,S),
          P\==avanzar,
          en(X,Y,S),
          asserta(en(X,Y,S1)),
		  asserta(estado(7,S)),
		  asserta(ya(2,S)).

% Accion distinta de Girar
est(S1):- S1 > 0,
          S is S1-1,
		   not(ya(4,S)),
          executedAction(P,S),
          orientacion(O,S),
          P\==girarDer,
          P\==girarIzq,
          asserta(orientacion(O,S1)),
		  asserta(estado(10,S)),
		  asserta(ya(4,S)).
		   
% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%                             Evaluación de las acciones                           %
% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% Es excelente salir si estoy en una casilla de salida con el candado abierto
excelente(salir,S) :-	orientacion(1,S),
						nivelActual(N),
						candadoAbierto(N),
						en(X,Y,S),
						esSalida(X,Y,N),
						hayCandado(X,Y,N),!.

% Es excelente salir si estoy en una casilla de salida sin candado
excelente(salir,S) :-	orientacion(1,S),
						nivelActual(N),
						en(X,Y,S),
						not(hayCandado(X,Y,N)),
						esSalida(X,Y,N),!.

excelente(girarDer,S) :-	orientacion(0,S),
							nivelActual(N),
							en(X,Y,S),
							esSalida(X,Y,N),
							not(hayCandado(X,Y,N)),!.

excelente(girarDer,S) :-	orientacion(3,S),
							nivelActual(N),
							en(X,Y,S),
							esSalida(X,Y,N),
							not(hayCandado(X,Y,N)),!.

excelente(girarIzq,S) :-	orientacion(2,S),
							nivelActual(N),
							en(X,Y,S),
							esSalida(X,Y,N),
							not(hayCandado(X,Y,N)),!.


% Es excelente tomar la llave si estoy en una casilla donde hay una llave (y no tengo una)
excelente(tomarLlave,S) :-	nivelActual(N),
							not(sostiene(N)),
							en(X,Y,S),
							hayLlave(X,Y,N),!.

% Es excelente abrir el candado si estoy en una casilla con candado y tengo la llave
excelente(abrirCandado,S) :-	nivelActual(N),
								not(candadoAbierto(N)),
								sostiene(N),
								en(X,Y,S),
								hayCandado(X,Y,N),!.

% *****************************************************************************
% ** Si hay miga en la celda, sigo el camino marcado **************************

% Si hay una miga delante, voy hacia adelante
miga(avanzar,S) :-	nivelActual(N),
					sostiene(N),
					en(X,Y,S),
					not(paredAdelante(X,Y,N)),
					not(hayCandado(X,Y,N)),
					posicionAdelante(X1,Y1,S),
					miga(X1,Y1,N),!.

% Si hay una miga a la derecha, giro a la derecha			   
miga(girarDer,S) :- nivelActual(N),
					sostiene(N),
					en(X,Y,S),
					not(paredDerecha(X,Y,N)),
					posicionDerecha(X1,Y1,S),
					miga(X1,Y1,N),!.

% Si hay una miga a la izquierda, giro a la izquierda
miga(girarIzq,S) :- nivelActual(N),
					sostiene(N),
					en(X,Y,S),
					not(paredIzquierda(X,Y,N)),
					posicionIzquierda(X1,Y1,S),
					miga(X1,Y1,N),!.

% Si hay una miga a atras, giro a la derecha
miga(girarDer,S) :- nivelActual(N),
					sostiene(N),
					en(X,Y,S),
					not(paredAtras(X,Y,N)),
					posicionAtras(X1,Y1,S),
					miga(X1,Y1,N),!.

manoDerecha(avanzar,S) :-	en(X,Y,S),
							nivelActual(N),
							paredDerecha(X,Y,N),
							not(paredAdelante(X,Y,N)),!.

manoDerecha(avanzar,S1) :-	en(X,Y,S1),
							nivelActual(N),
							not(paredDerecha(X,Y,N)),
							S is S1-1,
							executedAction(girarDer,S),!.
						
manoDerecha(girarDer,S1) :-	en(X,Y,S1),
							nivelActual(N),
							not(paredDerecha(X,Y,N)),!.

manoDerecha(girarIzq,S) :-	en(X,Y,S),
							nivelActual(N),
							paredAdelante(X,Y,N),!.

						
% *****************************************************************************
% ** Si no hay migas, avanzo hacia casillas menos visitadas *******************
				
% Avanzar si no hay pared adelante y es mi casilla vecina por la que menos veces pasé						   
contador(avanzar,S) :-	cuentaAtras(S,C2),
						cuentaDerecha(S,C3),
						cuentaAdelante(S,C1),
						cuentaIzquierda(S,C4),

						C1 =< C2,
						C1 =< C3,
						C1 =< C4,!.

% Gira Derecha si no hay pared a la derecha y ademas no paso por la celda a la derecha					   
contador(girarDer,S) :- cuentaAtras(S,C2),
						cuentaDerecha(S,C1),
						cuentaAdelante(S,C3),
						cuentaIzquierda(S,C4),

						C1 =< C2,
						C1 =< C3,
						C1 =< C4,!.	

% Gira Izquierda si no hay pared a la izquierda y ademas no paso por la celda a la izquierda					   						
contador(girarIzq,S) :- cuentaIzquierda(S,C1),
						cuentaDerecha(S,C2),
						cuentaAdelante(S,C3),
						cuentaAtras(S,C4),

						C1 =< C2,
						C1 =< C3,
						C1 =< C4,!.

% *****************************************************************************
% ** Si ninguna optimizacion funciona, avanzo o giroDerecha o GiroIzq *********

buena(avanzar,S) :-	nivelActual(N),
                    en(X,Y,S),
                    not(paredAdelante(X,Y,N)),!.

% Gira Derecha si no hay pared a la derecha 					   
buena(girarDer,S) :-	nivelActual(N),
						en(X,Y,S),
						not(paredDerecha(X,Y,N)),!.

% Gira Izquierda si no hay pared a la izquierda 					   						
buena(girarIzq,S) :-	nivelActual(N),
						en(X,Y,S),
						not(paredIzquierda(X,Y,N)),!.

% Gira a la derecha si no hay pared atras
buena(girarDer,S) :-	nivelActual(N),
						en(X,Y,S),
						not(paredAtras(X,Y,N)),!.

bestAction(noAction,S) :- goalReached(S),!.
bestAction(X,S) :- excelente(X,S),!.
bestAction(X,S) :- miga(X,S),!.
 bestAction(X,S) :- manoDerecha(X,S),!.
% bestAction(X,S) :- contador(X,S),!.
% bestAction(X,S) :- buena(X,S),!.
bestAction(_,_) :- fail,!.

goalReached(S):-  percepcion(nada,nada,noHayMas),situacionActual(S).



% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
%                             Extra sentences                                      %
% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Posicion Adelante %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%			
posicionAdelante(X,Y1,S):-	orientacion(0,S),
							en(X,Y,S),
							Y1 is Y-1.

posicionAdelante(X1,Y,S):-  orientacion(1,S),
							en(X,Y,S),
                            X1 is X+1.

posicionAdelante(X,Y1,S):-	orientacion(2,S),
							en(X,Y,S),
                            Y1 is Y+1.

posicionAdelante(X1,Y,S):-	orientacion(3,S),
							en(X,Y,S),
                            X1 is X-1.

% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Posicion Derecha %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
posicionDerecha(X1,Y,S):-	orientacion(0,S),
							en(X,Y,S),
							X1 is X+1.

posicionDerecha(X,Y1,S):-	orientacion(1,S),
							en(X,Y,S),
							Y1 is Y+1.

posicionDerecha(X1,Y,S):-	orientacion(2,S),
							en(X,Y,S),
							X1 is X-1.

posicionDerecha(X,Y1,S):-	orientacion(3,S),
							en(X,Y,S),
							Y1 is Y-1.							

% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Posicion Izquierda %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
posicionIzquierda(X1,Y,S):-	orientacion(0,S),
							en(X,Y,S),
							X1 is X-1.

posicionIzquierda(X,Y1,S):-	orientacion(1,S),
							en(X,Y,S),
							Y1 is Y-1.

posicionIzquierda(X1,Y,S):-	orientacion(2,S),
							en(X,Y,S),
							X1 is X+1.

posicionIzquierda(X,Y1,S):-	orientacion(3,S),
							en(X,Y,S),
							Y1 is Y+1.

% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Posicion Atras %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
posicionAtras(X,Y1,S):-	orientacion(0,S),
						en(X,Y,S),
						Y1 is Y+1.

posicionAtras(X1,Y,S):-	orientacion(1,S),
						en(X,Y,S),
						X1 is X-1.

posicionAtras(X,Y1,S):-	orientacion(2,S),
						en(X,Y,S),
						Y1 is Y-1.

posicionAtras(X1,Y,S):-	orientacion(3,S),
						en(X,Y,S),
						X1 is X+1.
							
% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%							
% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Pared Adelante %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

paredAdelante(X,Y,N) :-	situacionActual(S),
                        orientacion(0,S),
						en(X,Y,S),
                        paredNorte(X,Y,N),!.

paredAdelante(X,Y,N) :-	situacionActual(S),
						orientacion(1,S),
						en(X,Y,S),
						paredEste(X,Y,N).
paredAdelante(X,Y,N) :-	situacionActual(S),
						orientacion(1,S),
						en(X,Y,S),
						esSalida(X,Y,N),!.
						
paredAdelante(X,Y,N) :-	situacionActual(S),
                        orientacion(2,S),
						en(X,Y,S),
                        paredSur(X,Y,N),!.

paredAdelante(X,Y,N) :-	situacionActual(S),
                        orientacion(3,S),
						en(X,Y,S),
                        paredOeste(X,Y,N).
paredAdelante(_,Y,_) :-	situacionActual(S),
                        orientacion(3,S),
						en(0,Y,S),!.
% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Pared Derecha %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

paredDerecha(X,Y,N) :-	situacionActual(S),
                        orientacion(0,S),
						en(X,Y,S),
                        paredEste(X,Y,N).
paredDerecha(X,Y,N) :-	situacionActual(S),
                        orientacion(0,S),
						en(X,Y,S),
						esSalida(X,Y,N),!.
						
paredDerecha(X,Y,N) :-	situacionActual(S),
                        orientacion(1,S),
						en(X,Y,S),
                        paredSur(X,Y,N),!.

paredDerecha(X,Y,N) :-	situacionActual(S),
                        orientacion(2,S),
						en(X,Y,S),
                        paredOeste(X,Y,N).
paredDerecha(_,Y,_) :-	situacionActual(S),
                        orientacion(2,S),
						en(0,Y,S),!.
						
paredDerecha(X,Y,N) :-	situacionActual(S),
                        orientacion(3,S),
						en(X,Y,S),
                        paredNorte(X,Y,N),!.
						
% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Pared Izquierda %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

paredIzquierda(X,Y,N) :-	situacionActual(S),
							orientacion(0,S),
							en(X,Y,S),
							paredOeste(X,Y,N).
paredIzquierda(_,Y,_) :-	situacionActual(S),
							orientacion(0,S),
							en(0,Y,S),!.
							
paredIzquierda(X,Y,N) :-	situacionActual(S),
							orientacion(1,S),
							en(X,Y,S),
							paredNorte(X,Y,N),!.

paredIzquierda(X,Y,N) :-	situacionActual(S),
							orientacion(2,S),
							en(X,Y,S),
							paredEste(X,Y,N).
paredIzquierda(X,Y,N) :-	situacionActual(S),
							orientacion(2,S),
							en(X,Y,S),
							esSalida(X,Y,N),!.
							
paredIzquierda(X,Y,N) :-	situacionActual(S),
							orientacion(3,S),
							en(X,Y,S),
							paredSur(X,Y,N),!.

% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%% Pared Atras %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%

paredAtras(X,Y,N) :-	situacionActual(S),
						en(X,Y,S),
                        orientacion(0,S),
                        paredSur(X,Y,N),!.

paredAtras(X,Y,N) :-	situacionActual(S),
						en(X,Y,S),
						orientacion(1,S),
						paredOeste(X,Y,N).
paredAtras(_,Y,_) :-	situacionActual(S),
						en(0,Y,S),
						orientacion(1,S),!.
						
paredAtras(X,Y,N) :-	situacionActual(S),
						en(X,Y,S),
						orientacion(2,S),
						paredNorte(X,Y,N),!.

paredAtras(X,Y,N) :-	situacionActual(S),
						en(X,Y,S),
						orientacion(3,S),
						paredEste(X,Y,N).						
paredAtras(X,Y,N) :-	situacionActual(S),
						en(X,Y,S),
						orientacion(3,S),
						esSalida(X,Y,N),!.
						
% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Cuenta las veces que pasamos por una determinada casilla. %%%%%%%%%%%%%%%%%%%%%

% Cuenta la cantidad de veces que se paso por adelante
cuentaAdelante(S,C1) :-	en(X,Y,S),
						nivelActual(N),
						not(paredAdelante(X,Y,N)),
						posicionAdelante(X1,Y1,S),
						pasoPor(X1,Y1,C1,N),!.

cuentaAdelante(S,C1) :-	en(X,Y,S),
						nivelActual(N),
						not(paredAdelante(X,Y,N)),
						posicionAdelante(X1,Y1,S),
						not(pasoPor(X1,Y1,_,N)),C1 is 0,!.

cuentaAdelante(_,9999).

% Cuenta la cantidad de veces que se paso por la izquierda
cuentaIzquierda(S,C1) :-	en(X,Y,S),
							nivelActual(N),
							not(paredIzquierda(X,Y,N)),
							posicionIzquierda(X1,Y1,S),
							pasoPor(X1,Y1,C1,N),!.

cuentaIzquierda(S,C1) :-	en(X,Y,S),
							nivelActual(N),
							not(paredIzquierda(X,Y,N)),
							posicionIzquierda(X1,Y1,S),
							not(pasoPor(X1,Y1,_,N)),C1 is 0,!.

cuentaIzquierda(_,9999).	% Hay pared en esa dirección.

% Cuenta la cantidad de veces que se paso por la derecha
cuentaDerecha(S,C1) :-	en(X,Y,S),
						nivelActual(N),
						not(paredDerecha(X,Y,N)),
						posicionDerecha(X1,Y1,S),
						pasoPor(X1,Y1,C1,N),!.

cuentaDerecha(S,C1) :-	en(X,Y,S),
						nivelActual(N),
						not(paredDerecha(X,Y,N)),
						posicionDerecha(X1,Y1,S),
						not(pasoPor(X1,Y1,_,N)),C1 is 0,!.

cuentaDerecha(_,9999).

% Cuenta la cantidad de veces que se paso por atras
cuentaAtras(S,C1) :-	en(X,Y,S),
						nivelActual(N),
						not(paredAtras(X,Y,N)),
						posicionAtras(X1,Y1,S),
						pasoPor(X1,Y1,C1,N),!.

cuentaAtras(S,C1) :-	en(X,Y,S),
						nivelActual(N),
						not(paredAtras(X,Y,N)),
						posicionAtras(X1,Y1,S),
						not(pasoPor(X1,Y1,_,N)),C1 is 0,!.

cuentaAtras(_,9999).

% %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
% Contador de giros %%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%%
giro4veces(S) :- 	situacionActual(S),
					en(X,Y,S),
					nivelActual(N),
					not(paredAdelante(X,Y,N)),
					S1 is S-1,
					S2 is S1-1,
					S3 is S2-1,
					S4 is S3-1,
					executedAction(girarDer,S1),
					executedAction(girarDer,S2),
					executedAction(girarDer,S3),
					executedAction(girarDer,S4).
					
giro4vecesI(S) :- 	situacionActual(S),
					en(X,Y,S),
					nivelActual(N),
					not(paredAdelante(X,Y,N)),
					S1 is S-1,
					S2 is S1-1,
					S3 is S2-1,
					S4 is S3-1,
					executedAction(girarIzq,S1),
					executedAction(girarIzq,S2),
					executedAction(girarIzq,S3),
					executedAction(girarIzq,S4).
