import React from "react";
import { SnackMessageType } from "../../types/entityType";

import '../../styles/SnackMessage.css';

type SnackMessageTypeProps = {
  message: SnackMessageType,
  closeAction: () => void
}

const SnackMessage: React.FC<SnackMessageTypeProps> = ({
  message,
  closeAction
}) => {
  return (
    <div className={`snack-message ${message.type}`}>
      <div className="btn-container">
        <div className="close-btn" onClick={closeAction}>
          <i className="fa fa-times"></i>
        </div>
      </div>
      <p>{message.message}</p>
    </div>
  );
}

export default SnackMessage;