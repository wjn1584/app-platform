import useSearchParams from "./useSearchParams";

const PLUGINS = [
  {
    "name": "pathobot",
    // "url": "/plugins/pathbot/index.html#/chat?sidebar=0"  // prod
    "url": "http://localhost:3099/#/chat?sidebar=0"       // dev
  }
];

interface Plugin {
  name: string;
  url: string;
}

function usePlugin(): Plugin | undefined {
  const { plugin_name } = useSearchParams();
  return PLUGINS.find(item => item.name === plugin_name);
}

export default usePlugin;
