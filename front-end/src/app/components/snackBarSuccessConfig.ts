import {MatSnackBarConfig} from "@angular/material/snack-bar";

export default function snackBarSuccessConfig()  {
  let conf: MatSnackBarConfig = {
    panelClass: "snackbar-success",
    duration: 4000,
    horizontalPosition: "right",
    verticalPosition: "top"
  };

  return conf;
}
