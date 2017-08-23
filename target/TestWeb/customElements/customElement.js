class HappyDuke extends HTMLElement {
    
	//Called whenever the custom element is inserted into the DOM.
    connectedCallback() {
        console.log("duke initialized");
    }
    //Called whenever the custom element is removed from the DOM
    disconnectedCallback(){}

    static get observedAttributes() {
        return  ["message"];
    }
    
    //Called whenever an attribute is added, removed or updated. Only attributes listed in the observedAttributes property are affected.
    attributeChangedCallback(name, oldValue, newValue) {
        console.log(`dukes attributeChangedCallback attr name: ${name}, oldValue: ${oldValue}, newValue: ${newValue}`);
        this.innerHTML = newValue;
    }
}
customElements.define("happy-duke", HappyDuke);


