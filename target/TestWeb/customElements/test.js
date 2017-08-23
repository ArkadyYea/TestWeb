let a = 123;

function abc() {
    console.log("Some string from function abc()");
    return "Some string from function abc()";
}

function fetchFun() {
    fetch("/TestWeb/res/test/json").then(response => console.log(response));
}
function fetchFun2() {
    fetch("/TestWeb/res/test/json").then(response => response.json()).then(json => updateOutput(json) );
}
function fetchFun3() {
    fetch("/TestWeb/res/test/jsonss").then(response => response.json()).then(json => console.log(json) );
}

function fetchFun4() {
    fetch("/TestWeb/res/test/jsonss").then(response => response.json()).then(json => updateOutput2(json) );
}

function updateOutput(payLoad) {
	console.log("updateOutput");
	let output = document.querySelector("#in2");
    output.value = payLoad.name +" "+payLoad.surname;
}

function updateOutput2(payLoad) {
	console.log("updateOutput2");
	let output = document.querySelector("#in2");
	//output.value = "abcdcba";
    output.value = payLoad[1].name +" "+payLoad[1].surname;
}

class Test {

    constructor() {
        console.log("Test class initialized.");
        this.testFun();
        //this.fetchFun2();
    }

    testFun() {
        this.output = document.querySelector("#in1");
        this.output.value = "Test.testFun() called";
    }
    
    static fetchFun() {
    	console.log("Test.fetchFun()");
        fetch("/TestWeb/res/test/json")
            .then(response => response.json())
            .then(json => this.updateOutput(json));
    }
    static updateOutput(payLoad) {
    	console.log("Test.updateOutput");
    	let output = document.querySelector("#in1");
        output.value = payLoad.name +" "+payLoad.surname;
    }
    
    fetchFun2() {
    	console.log("Test.fetchFun2()");
        fetch("/TestWeb/res/test/json").then(response => console.log(response));
    }
}   

let test = new Test();

