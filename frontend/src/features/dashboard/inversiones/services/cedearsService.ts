const baseUrl = `${process.env.NEXT_PUBLIC_BASE_API_URL}/perfil`;

export async function getCedears(id: string) {
  const res = await fetch(`${baseUrl}/${id}`);
  const data = await res.json();
  return data.data;
}
