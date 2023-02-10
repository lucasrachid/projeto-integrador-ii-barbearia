
import {Role} from "./role";
import Auditable from './auditable';

export default class User extends Auditable {

  id?: number;
  name?: string;
  email?: string;
  cpf?: string;
  birthday?: Date;
  username?: string;
  password?: string;
  roles?: Role[];

  // Auditable Properties
  override active?: boolean;
  override createdBy?: string;
  override createdDate?: Date;
  override lastModifiedBy?: string;
  override lastModifiedDate?: Date;

  constructor(
    id?: number,
    name?: string,
    cpf?: string,
    birthday?: Date,
    username?: string,
    password?: string,
    email?: string,
    roles?: Role[],

    // Auditable Properties
    active?: boolean,
    createdBy?: string,
    createdDate?: Date,
    lastModifiedBy?: string,
    lastModifiedDate?: Date
  ) {
    super();
    this.id = id;
    this.name = name;
    this.cpf = cpf;
    this.birthday = birthday;
    this.username = username;
    this.password = password;
    this.email = email;
    this.roles = roles;

    // Auditable Properties
    this.active = active;
    this.createdBy = createdBy;
    this.createdDate = createdDate;
    this.lastModifiedBy = lastModifiedBy;
    this.lastModifiedDate = lastModifiedDate;

  }
}
