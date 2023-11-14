import React from "react";
import Axios from "axios";

class ImageContainer extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            images: []
        };
    }

    componentDidMount() {
        // On récupère les images depuis l'API
        Axios.get("http://localhost:8080/images")
            .then((res) => {
                // On met à jour le state avec les images reçues
                this.setState({
                    images: res.data
                });
            })
            .catch((err) => {
                console.log(err);
            });
    }
    render() {
        return (
            <div className="image-container">
                {
                this.state.images.length > 0 ?
                this.state.images.map((image) => {
                    return (
                        <div className="image">
                            <img src={image.url} alt={image.name} />
                            <p>{image.name}</p>
                        </div>
                    );
                }) :
                <p>No images</p>
                }
            </div>
        );
    }
}

export default ImageContainer;