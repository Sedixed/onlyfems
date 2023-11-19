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
    <div className="snack-message">
      <p>{message.message}</p>
    </div>
  );
}

export default SnackMessage;