import { StatusResponse } from './status-response';

export class AuthResponse {
  userId?: number;
  gmail?: string;
  name?: string;
  phoneNo?: string;
  companyName?: string;
  createdOn?: string;
  updateOn?: string;
  status?: StatusResponse;
  constructor(
    userId?: number,
    gmail?: string,
    name?: string,
    phoneNo?: string,
    companyName?: string,
    createdOn?: string,
    updateOn?: string,
    status?: StatusResponse
  ) {
    this.userId = userId;
    this.gmail = gmail;
    this.phoneNo = phoneNo;
    this.companyName = companyName || '';
    this.createdOn = createdOn;
    this.updateOn = updateOn;
    this.status = status;
    this.name=name;
  }
}
