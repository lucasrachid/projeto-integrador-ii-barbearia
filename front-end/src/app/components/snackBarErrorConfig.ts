import {MatSnackBarConfig} from "@angular/material/snack-bar";

export default function snackBarErrorConfig()  {
  let conf: MatSnackBarConfig = {
    panelClass: "snackbar-error",
    duration: 2000,
    horizontalPosition: "right",
    verticalPosition: "top"
  };

  return conf;
}
