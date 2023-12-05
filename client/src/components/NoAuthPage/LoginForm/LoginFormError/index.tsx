import React from "react"

type LoginFormErrorPropsType = {
  message: string,
}

const LoginFormError: React.FC<LoginFormErrorPropsType> = ({
  message,
}) => {
  return (
    <div className="login-form-error">
      <p>{message}</p>
    </div>
  )
}

export default LoginFormError