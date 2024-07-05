window.addEventListener("load", function () {
  var spinner = document.querySelector("#spinnerload");
  var backspinner = document.querySelector("#overlay");
  if (spinner) {
    setTimeout(function () {
      backspinner.style.display = "none";
      spinner.style.display = "none";
    }, 1500);
  }
});
