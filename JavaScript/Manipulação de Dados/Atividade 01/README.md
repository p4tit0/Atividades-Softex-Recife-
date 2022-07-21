# ***Primeira atividade sobre Manipulação de Dados***
_Aluno: Vinícius Santos Lima  E-mail: viniciussantoslima2003@gmail.com<br>Data: 21 de julho de 2022_
#  

### Enunciado: 

Acesse pelo menos dois sites de sua preferência e os inspecione com o botão direito do mouse na página web.<br>
Com o código fonte aberto você deve:<br>
<br>
Verificar no código algum elemento que utilize JavaScript; <br>
Marcar alguns elementos do site; <br>
Explicar como ele se comporta. <br>Exemplo: entrar no site do Google, inspecionar o botão de pesquisa, verificar o código e explicar qual a finalidade do botão.<br>
<br>
**Trabalhe esse código em seu IDE, suba ele para sua conta no GitHub e compartilhe o link desse projeto no campo ao lado para que outros desenvolvedores possam analisá-lo.**

<h3><a href="https://pt.wikipedia.org/wiki/O_Jogo_(jogo_mental)">Primeiro site:</a></h3>
Clique no hiperlink acima ou acesse o arquivo "https://pt.wikipedia.org/wiki/O_Jogo_(jogo_mental)" para acessar o site.<br>
<br>
O elemento escolhido é o pequeno ”X” no banner do topo da página.
<img src="/Assets/Imagens/inspeção/elemento.png" width="960">
O código deste elemento é bem simples. Composto por apenas 2 linhas descritas a seguir

```js
mw.centralNotice.hideBanner();
return false;
```

Basicamente ele esconde o baner mostrado na imagem abaixo e, por algum motivo desconhecido, tem o valor lógico falso como retorno.
<img src="/Assets/Imagens/inspeção/banner.png" width="960">

<br>
<h3><a href="https://theuselessweb.com">Segundo site:</a></h3>
Clique no hiperlink acima ou acesse o arquivo "https://theuselessweb.com" para acessar o site.<br>

O elemento escolhido nesse site é um dos poucos que utiliza JavaScript, o grade botão com a palavra "PLEASE".
<img src="/Assets/Imagens/inspeção/useless.png" width="960">

seu código esta descrito abaixo (para acessá-lo diretamente acesse https://d33wubrfki0l68.cloudfront.net/js/8782a87f79471137c3ddd9bd766cec0b2f8af8ee/js/uselessweb.js)

```js
function uselessWebButton(button, popup) {
	var buttonElement = button
	var popupElement = popup
	var initialClick = false
	var randomRange = 7

	var sitesList = [
		"https://longdogechallenge.com/",
		"https://checkboxrace.com/",
		"https://onesquareminesweeper.com/",
		"http://heeeeeeeey.com/",
		"http://corndog.io/",
		"https://binarypiano.com/",
		"https://mondrianandme.com/",
		"https://puginarug.com",
		"http://floatingqrcode.com/",
		"https://checkboxolympics.com/",
		"https://alwaysjudgeabookbyitscover.com",
		"https://thatsthefinger.com/",
		"https://cant-not-tweet-this.com/",
		"https://cursoreffects.com",
		"http://eelslap.com/",
		"http://www.staggeringbeauty.com/",
		"http://burymewithmymoney.com/",
		"https://smashthewalls.com/",
		"https://jacksonpollock.org/",
		"http://endless.horse/",
		"http://drawing.garden/",
		"https://www.trypap.com/",
		"http://www.republiquedesmangues.fr/",
		"http://www.movenowthinklater.com/",
		"http://www.rrrgggbbb.com/",
		"http://www.koalastothemax.com/",
		"http://www.everydayim.com/",
		"http://randomcolour.com/",
		"http://cat-bounce.com/",
		"http://chrismckenzie.com/",
		"https://thezen.zone/",
		"http://hasthelargehadroncolliderdestroyedtheworldyet.com/",
		"http://ninjaflex.com/",
		"http://ihasabucket.com/",
		"http://corndogoncorndog.com/",
		"http://www.hackertyper.com/",
		"https://pointerpointer.com",
		"http://imaninja.com/",
		"http://www.partridgegetslucky.com/",
		"http://www.ismycomputeron.com/",
		"http://www.nullingthevoid.com/",
		"http://www.muchbetterthanthis.com/",
		"http://www.yesnoif.com/",
		"http://lacquerlacquer.com",
		"http://potatoortomato.com/",
		"http://iamawesome.com/",
		"https://strobe.cool/",
		"http://thisisnotajumpscare.com/",
		"http://doughnutkitten.com/",
		"http://crouton.net/",
		"http://corgiorgy.com/",
		"http://www.wutdafuk.com/",
		"http://unicodesnowmanforyou.com/",
		"http://chillestmonkey.com/",
		"http://scroll-o-meter.club/",
		"http://www.crossdivisions.com/",
		"http://tencents.info/",
		"https://boringboringboring.com/",
		"http://www.patience-is-a-virtue.org/",
		"http://pixelsfighting.com/",
		"http://isitwhite.com/",
		"https://existentialcrisis.com/",
		"http://onemillionlols.com/",
		"http://www.omfgdogs.com/",
		"http://oct82.com/",
		"http://chihuahuaspin.com/",
		"http://www.blankwindows.com/",
		"http://tunnelsnakes.com/",
		"http://www.trashloop.com/",
		"http://www.ascii-middle-finger.com/",
		"http://spaceis.cool/",
		"http://www.doublepressure.com/",
		"http://www.donothingfor2minutes.com/",
		"http://buildshruggie.com/",
		"http://buzzybuzz.biz/",
		"http://yeahlemons.com/",
		"http://wowenwilsonquiz.com",
		"https://thepigeon.org/",
		"http://notdayoftheweek.com/",
		"http://www.amialright.com/",
		// "http://nooooooooooooooo.com/",
		"https://greatbignothing.com/",
		"https://zoomquilt.org/",
		"https://dadlaughbutton.com/",
		"https://remoji.com/",
		"http://papertoilet.com/",
		"https://loopedforinfinity.com/",
		"https://www.bouncingdvdlogo.com/",
		"https://findtheinvisiblecow.com/"
	]

	var sites = null

	// Prepares and binds the button
	var init = function () {
		button.onclick = onButtonClick

		// Initial set sites
		sites = sitesList.slice()

		if (localStorage["currentSiteList"]) {
			// They have storage, filter out any not in the base list, that could be spam now
			var currentSites = JSON.parse(localStorage["currentSiteList"])
			var filteredSites = currentSites.filter(
				(site) => sitesList.indexOf(site) !== -1
			)
			if (filteredSites.length > 0) {
				sites = filteredSites
			}
		}
	}

	// Selects and removes the next website from the list
	var selectWebsite = function () {
		var site, range, index

		range = randomRange > sites.length ? sites.length : randomRange
		index = Math.floor(Math.random() * range)

		site = sites[index]
		sites.splice(index, 1)

		return site
	}

	var openSite = function (url) {
		window.open(url)
	}

	var onButtonClick = function () {
		if (window.gtag) {
			gtag("event", "click", { event_category: "button" })
		}

		if (initialClick === false) {
			// Change text from "TO A"
			document.getElementById("joint").innerHTML = "TO ANOTHER"
			initialClick = true
		}

		var url = selectWebsite()
		openSite(url)

		// User has visited ALL the sites... refresh the list.
		if (sites.length == 0) {
			sites = sitesList.slice()
		}

		localStorage["currentSiteList"] = JSON.stringify(sites)
	}

	init()
}

var uselessWebButton = new uselessWebButton(document.getElementById("button"));
```

Em resumo o código cria uma enorme lista contendo o endereço de diversos sites inúteis. na primeira vez que o usuário pressiona o botão ele troca o texto do site de "TO A" para "TO ANOTHER", após isso, o código seleciona um dos siters da lista e o remove da lista de possíveis sites para que ele não se repita. Nas vezes seguintes esse processo se repete, no entanto o texto do site não é alterado. Por fim, caso todos os sites tenham sido visitados a lista de possíveis sites é resetada.
