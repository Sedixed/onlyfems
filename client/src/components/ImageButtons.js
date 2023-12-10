import React from "react";

import axios from "axios";

class ImageButtons extends React.Component {
    constructor(props) {
        super(props);

        this.state = {
            imageName: "",
            imageBody: "",
        };
    }

    handleAddRandomClick = () => {
        // On récupère une image aléatoire depuis l'API de Unsplash
        axios.get("https://source.unsplash.com/random")
            .then((res) => {
                // On met à jour le state avec le nom de l'image et son base64
                axios.post("http://localhost:8080/image", {
                    name: res.request.responseURL,
                    encodedImage: res.data,
                    publicity: true,
                    description: "Random image from Unsplash"
                })
                .then((res) => {
                    console.log(res);
                })
                .catch((err) => {
                    console.log(err);
                    console.log("Add image error");
                    console.log(err.stack);
                });
            })
            .catch((err) => {
                console.log(err);
                console.log("Get random image error");
                console.log(err.stack);
            });        
    }

    handleAddClick = () => {
        console.log("Yay, you clicked me!")
    }

    render() {
        return (
            <div className="image-buttons">
                <button onClick={this.handleAddRandomClick}>Add random image to DB</button>
                <button onClick={this.handleAddClick}>Add image to DB</button>
            </div>
        );
    }
}

export default ImageButtons;