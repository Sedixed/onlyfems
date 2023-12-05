import React from "react";
import { SnackMessageType } from "../../types/entityType";

import '../../styles/SnackMessage.css';

type SnackMessageTypeProps = {
  snackMessage: SnackMessageType,
  closeAction: () => void
}

const SnackMessage: React.FC<SnackMessageTypeProps> = ({
  snackMessage,
  closeAction
}) => {
  return (
    <div className={`snack-message ${snackMessage.type}`}>
      <div className="btn-container">
        <div className="close-btn" onClick={closeAction}>
          <i className="fa fa-times"></i>
        </div>
      </div>
      <p>{snackMessage.message}</p>
    </div>
  );
}

export default SnackMessage;