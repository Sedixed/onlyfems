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
                this.setState({
                    imageName: res.request.responseURL.split("/")[3],
                    imageBody: res.data
                });
            })
            .catch((err) => {
                console.log(err);
            });
        
        // Puis on ajoute l'image à la DB
        axios.post("http://localhost:8080/images", {
            name: this.state.imageName,
            body: this.state.imageBody
        })
        .then((res) => {
            console.log(res);
        })
        .catch((err) => {
            console.log(err);
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