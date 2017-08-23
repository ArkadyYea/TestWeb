//<template>
//    <style>
//        .vimeo {
//            background-color: #000;
//            margin-bottom: 30px;
//            position: relative;
//            padding-top: 56.25%;
//            overflow: hidden;
//            cursor: pointer;
//        }
//        .vimeo img {
//            width: 100%;
//            top: -16.82%;
//            left: 0;
//            opacity: 0.7;
//        }
//        .vimeo .play-button {
//            width: 90px;
//            height: 60px;
//            background-color: #333;
//            box-shadow: 0 0 30px rgba( 0,0,0,0.6 );
//            z-index: 1;
//            opacity: 0.8;
//            border-radius: 6px;
//        }
//        .vimeo .play-button:before {
//            content: "";
//            border-style: solid;
//            border-width: 15px 0 15px 26.0px;
//            border-color: transparent transparent transparent #fff;
//        }
//        .vimeo img,
//        .vimeo .play-button {
//            cursor: pointer;
//        }
//        .vimeo img,
//        .vimeo iframe,
//        .vimeo .play-button,
//        .vimeo .play-button:before {
//            position: absolute;
//        }
//        .vimeo .play-button,
//        .vimeo .play-button:before {
//            top: 50%;
//            left: 50%;
//            transform: translate3d( -50%, -50%, 0 );
//        }
//        .vimeo iframe {
//            height: 100%;
//            width: 100%;
//            top: 0;
//            left: 0;
//        }
//    </style>
//    <div class="vimeo">
//        <div class="play-button"></div>
//    </div>
//</template>


class MoreComplex extends HTMLElement {
    
    //connectedCallback() is called when (after) the element is attached to the DOM.
    connectedCallback() {
        console.log("more-complex initialized (connectedCallback)");
        
    }
    disconnectedCallback(){
    	console.log("more-complex disconnectedCallback");
    }

    static get observedAttributes() {
        return  ["message"];
    }

    attributeChangedCallback(name, oldValue, newValue) {
        console.log(`more-complexs attributeChangedCallback attr name: ${name}, oldValue: ${oldValue}, newValue: ${newValue}`);
        //this.innerHTML = newValue;
        this.innerHTML = '<h3>Hello '+newValue+ ' from MoreComplex class</h3>';
    }
}
customElements.define("more-complex", MoreComplex);




