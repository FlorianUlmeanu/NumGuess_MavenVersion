function reset(){
    document.getElementById("serverResponse").innerText="";
    xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange=callback;
    var url ="NumGenServlet"+"?requestRestartGame=1";
    xmlHttp.open("GET",url,true);
    xmlHttp.send();
}

function guess(){
    xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange=callback;
    var url ="NumGenServlet"+"?requestGuessNumber="+document.getElementById("number").value;
    xmlHttp.open("GET",url,true);
    xmlHttp.send();
}

function guessLink(givenValue) {
    xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange=callback;
    var url ="NumGenServlet"+"?requestGuessNumber="+givenValue;
    xmlHttp.open("GET",url,true);
    xmlHttp.send();
}

function callback() {
    if(xmlHttp.readyState==4 && xmlHttp.status==200) {
        var jSonMessage = JSON.parse(xmlHttp.responseText);
        var keyRestartGame=jSonMessage.keyRestartGame;
        if (keyRestartGame != undefined && keyRestartGame.length > 0) {
            alert("The game was restarted!");
            document.getElementById("number").value="";
            location.reload();
            return;
        }

        var keyError = jSonMessage.keyError;
        if (keyError != undefined && keyError.length > 0) {
            alert("Please enter a value!");
            return;
        }
        var keySuccess = jSonMessage.keySuccess;
        var keyHint = jSonMessage.keyHint;
        var keyNrGuesses = jSonMessage.keyNrGuesses;
        var keyTimeTookToGuess = jSonMessage.timeToGuess;

        if(keySuccess=="false") {
            if (keyHint == "higher")
                document.getElementById("serverResponse").innerHTML = "WRONG, Try a Higher one!";
            else if (keyHint == "lower")
                document.getElementById("serverResponse").innerHTML = "WRONG, Try a Lower one!";
            else if (keyHint == "incorrect")
                document.getElementById("serverResponse").innerHTML = "Incorrect value!\n Please enter a value between 1 and 10";
        }
        else
        if(keySuccess=="true")
        {

            document.getElementById("guessButton").style.display = 'none';
            document.getElementById("navigation").style.display = 'none';
            document.getElementById("postcontent").style.display = 'none';
            document.getElementById("number").style.display = 'none';

            document.getElementById("serverResponse").innerHTML = "Congrats, you guessed the number after " + keyNrGuesses + " guesses.</br> It took:"+keyTimeTookToGuess;
            //document.getElementById("counter").innerHTML = "It took you " + keyTimeTookToGuess + " seconds.";

        }
    }
}

function validate(evt) {
    var theEvent = evt || window.event;
    var key = theEvent.keyCode || theEvent.which;
    key = String.fromCharCode(key);
    var regex = /[0-9]/;
    if ((!regex.test(key)) && !(evt.keyCode == 46 || evt.keyCode == 8 || evt.keyCode == 37 || evt.keyCode == 39)){
        theEvent.returnValue = false;
        if (theEvent.preventDefault) theEvent.preventDefault();
    }
}