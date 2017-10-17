import {abc, myFunction2, myFunction3} from './export.js';
import {myFunction2 as def} from './export.js';
import * as myModule from './export.js';							//MyModule is a namespace where names are imported, used as prefix
import myDefault, * as myModule2 from './export.js';				//Default import has to go first
import defaultExportmyBeImportedWithAnyName from './export.js';		//A default export can be imported with any name,

console.log('------------------------------------');
console.log('export { myFunction as abc, myFunction2 } - abc() -> '+abc());     					//export { myFunction as abc, myFunction2 };
console.log('export { myFunction as abc, myFunction2 } - myFunction2() -> '+myFunction2());			//export { myFunction as abc, myFunction2 };
console.log('export { myFunction3 } - myFunction3() -> '+myFunction3());     						//export { myFunction3 };

console.log('------------------------------------');
console.log('import {myFunction2 as def} - myFunction2() -> '+def());     							//import {myFunction2 as def};

console.log('------------------------------------');
console.log('import myDefault, * as myModule - myModule.abc() -> '+myModule.abc());     			//import myDefault, * as myModule;
console.log('import myDefault, * as myModule - myModule.myFunction2() -> '+myModule.myFunction2()); //import myDefault, * as myModule;
console.log('import myDefault, * as myModule - myModule.myFunction3() -> '+myModule.myFunction3()); //import myDefault, * as myModule;
console.log('import myDefault, * as myModule - myModule.myFunction5() -> '+myModule.myFunction5()); //import myDefault, * as myModule;
//console.log('Wont work, not explicitly imported - myFunction5() -> '+myFunction5());    			//Wont work, not explicitly imported

console.log('------------------------------------');
console.log('import myDefault, * as myModule - myDefault() -> '+myDefault());						//import myDefault, * as myModule from './export.js';
//console.log('import myDefault, * as myModule - myModule.myFunction4() '+myModule.myFunction4());	//Wont work
//console.log('import myDefault, * as myModule - myModule.myDefault() '+myModule.myDefault());		//Wont work

console.log('------------------------------------');												//import defaultExportmyBeImportedWithAnyName from './export.js';
console.log('import defaultExportmyBeImportedWithAnyName (imports default myFunction4) - defaultExportmyBeImportedWithAnyName() -> '+defaultExportmyBeImportedWithAnyName());	




let p1 = document.querySelector('#p1');
p1.innerHTML = abc();