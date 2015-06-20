
var AppSettings = new Object();

var app = {
    SOME_CONSTANTS : false,  // some constant

    initialize: function() {
        console.log("console log init");
        this.bindEvents();
        this.initFastClick();
        
    },
    
    bindEvents: function() {
        document.addEventListener('deviceready', this.onDeviceReady, false);
    },
    initFastClick : function() {
        window.addEventListener('load', function() {
            FastClick.attach(document.body);
        }, false);
    },

    startSequence: function() {
location = fire.html;
    },


    onDeviceReady: function() {
        console.log("device ready, start making you custom calls!");
		    myService = cordova.plugins.myService;;
        go();
		    checkFirstRun();
        //Moar code

    }

   
};


function checkFirstRun()
{
	if(typeof( window.localStorage.WoSApp) == "undefined")
	{
		//go to config page
		window.location= "#firstrun-page";
		//populate details into settings var

		document.addEventListener("backbutton", onBackKeyDown, false);
        function onBackKeyDown(e) {
          e.preventDefault();
        }
	}
}


function populateSettings()
{

AppSettings.name =$("#name").val();
AppSettings.ph =$("#phone").val();

AppSettings.contact1={}
AppSettings.contact1.name = $("#name1").val();
AppSettings.contact1.number = $("#num1").val();

AppSettings.contact2={}
AppSettings.contact2.name = $("#name2").val();
AppSettings.contact2.number = $("#num2").val();

AppSettings.contact3={}
AppSettings.contact3.name = $("#name3").val();
AppSettings.contact3.number = $("#num3").val();

window.location= "#home-page";
window.localStorage.setItem("WoSApp",JSON.stringify(AppSettings));
alert("Settings stored successfully")


}

function reconfigureSettings()
{
window.location= "#firstrun-page";

AppSettings = JSON.parse(window.localStorage.getItem("WoSApp"));

$("#name").val(AppSettings.name);

$("#phone").val(AppSettings.ph);
$("#name1").val(AppSettings.contact1.name);
$("#num1").val(AppSettings.contact1.number);
$("#name2").val(AppSettings.contact2.name);
$("#num2").val(AppSettings.contact2.number);
$("#name3").val(AppSettings.contact3.name);
$("#num3").val(AppSettings.contact3.number);
}

var myShakeEvent = new Shake({
    threshold: 15, // optional shake strength threshold
    timeout: 1000 // optional, determines the frequency of event generation
});

myShakeEvent.start();
var shaking = false;

window.addEventListener('shake', shakeEventDidOccur, false);
document.addEventListener("volumedownbutton", onVolumeDownKeyDown, false);

//function to call when shake occurs
function shakeEventDidOccur () {
    shaking = true;
    setTimeout( function() { shaking = false;} , 1000 );
}


    
function onVolumeDownKeyDown() {
         //put your own code here etc.
    if(shaking)
        alert('shake+volume!!');
}

function go() {
   myService.getStatus(function(r){startService(r)}, function(e){handleError(e)});
};

function startService(data) {
   if (data.ServiceRunning) {
      enableTimer(data);
   } else {
      myService.startService(function(r){enableTimer(r)}, function(e){handleError(e)});
   }
}

function enableTimer(data) {
   if (data.TimerEnabled) {
      allDone();
   } else {
      myService.enableTimer(6000, function(r){allDone(r)}, function(e){handleError(e)});
   }
}

function allDone() {
 //  alert("Service now running");
}