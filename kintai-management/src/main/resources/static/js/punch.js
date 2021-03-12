function set2fig(num) {
    if(num < 10) {
        num = "0" + num;
    }
   return num;
}
function showClock() {
   var nowTime = new Date();
   var nowHour = set2fig(nowTime.getHours());
   var nowMin  = set2fig(nowTime.getMinutes());
   var nowSec  = set2fig(nowTime.getSeconds());
   var time = nowHour + ":" + nowMin + ":" + nowSec;
   document.getElementById("clock").innerHTML = time;
}
setInterval('showClock()',1000);