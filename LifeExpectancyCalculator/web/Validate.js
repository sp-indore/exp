var n;
var yr;
var sex;
var request;
var life;
var counter=0;
var id;
function setLife()
{
	if(document.General.sex[0].checked==true)
	{
		life=parseInt(77);
	document.getElementById("show").innerHTML="Male's normal Life Expectancy is "+life+" years";
	}
	if(document.General.sex[1].checked==true)
	{
		life=parseInt(81);
	document.getElementById("show").innerHTML="Female's normal Life Expectancy is "+life+" years";
	}
}
function validateGeneral()
{
	 n=General.name.value;
	 yr=General.year.value;
	
	if(document.General.sex[0].checked==true)
	{
		sex="male";
	}
	if(document.General.sex[1].checked==true)
	{
		sex="female";
	}
	if(!(document.General.sex[0].checked||document.General.sex[1].checked))
	{
		alert("You are requested to complete the requirements");
		return false;
	}
	if(n==""||n==null)
	{
	alert("Field must not be empty");
	return false;
	}
	if(yr.length!=4)
	{
		alert("Invalid birth year");
		General.year.focus();
		return false;
	}
	if(yr==""||yr==null)
	{
	alert("Field must not be empty");
	General.year.focus();
	return false;	
	}
	 if(isNaN(yr)==true)
	{
		alert("Enter a valide birth year");
		return false;
	}
        if(parseInt(yr)>2016)
        {
            alert("Your age is too low to check");
            return false;
        }
        if(parseInt(yr)<1915)
        {
            alert("Birth year not acceptable");
            return false;
        }
	
	return true;

}
function count(id)
{
    counter++;
}
function checkCompletion()
{
    if(counter<10)
        {
        alert("Complete the requirements to proceed");
        return false;
        }
        return true;
}


