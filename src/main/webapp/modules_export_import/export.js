//Named exports are useful to export several values. During the import, it is mandatory to use the same name of the corresponding object.
//Default export is used to export a single class, function or primitive from a script file.
//There can be only one default export
//A default export can be imported with any name,

export { myFunction as abc, myFunction2 };
export { myFunction3 };
export default myFunction4;



function myFunction() {
    return 'myFunction() called';
}

function myFunction2() {
    return 'myFunction2() called';
}

function myFunction3() {
    return 'myFunction3() called';
}

function myFunction4() {
    return 'myFunction4() called';
}

export function myFunction5() {
    return 'myFunction5() called';
}