import React from "react";

import ImageContainer from "./components/ImageContainer";
import ImageButtons from "./components/ImageButtons";
import './App.css';

const App = () => {
    return (
        <div className="container">
            <h1>Images</h1>
            <ImageContainer />
            <ImageButtons />
        </div>
    );
};

export default App;
