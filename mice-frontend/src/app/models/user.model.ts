export class User {
  id!: number;

    cin!: number;
    username!: string;
    firstName!: string;
    lastName!: string;
    email!: string;
    password!: string;
    adresse!: string;
    creationDate!: Date;
    modificationDate!: Date;
    status!: boolean;
    role?: string;  

  
    constructor(data?: Partial<User>) {
      if (data) {
        Object.assign(this, data);
      }
    }
  }
  