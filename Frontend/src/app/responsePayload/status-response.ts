export class StatusResponse {
  statusId?: number;
  status?: string;
  statusMessage?: string;
  constructor(statusId?: number, status?: string, statusMessage?: string) {
    this.status=status || ''; 
    this.statusId=statusId || 0; 
    this.statusMessage=statusMessage || '';
  }
}
